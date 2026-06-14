import re
import sys
import time
import requests

API_URL = "http://localhost:5000"
INPUT_FILE = "src/main/resources/messages.properties"
LANGUAGES = {
    "es": "Spanish",
    "de": "German",
    "fr": "French",
    "pt": "Portuguese",
    "zh": "Chinese",
    "ja": "Japanese",
    "ar": "Arabic",
    "ru": "Russian"
}


def protect_placeholders(text: str):
    """Replace {0}, {0,date,short}, etc. with safe tokens before translating."""
    tokens = []

    def replacer(match):
        token = f"XPHX{len(tokens)}X"
        tokens.append(match.group(0))
        return token

    protected = re.sub(r"\{[^}]*\}", replacer, text)
    return protected, tokens


def restore_placeholders(text: str, tokens: list) -> str:
    for i, original in enumerate(tokens):
        text = text.replace(f"XPHX{i}X", original)
    return text


def translate_value(value: str, target_lang: str) -> str:
    if not value.strip():
        return value

    protected, tokens = protect_placeholders(value)

    try:
        response = requests.post(
            f"{API_URL}/translate",
            json={"q": protected, "source": "en", "target": target_lang, "format": "text"},
            timeout=30,
        )
        response.raise_for_status()
        translated = response.json()["translatedText"]
        return restore_placeholders(translated, tokens)
    except Exception as e:
        print(f"  ⚠ Translation failed for '{value[:40]}...': {e}", file=sys.stderr)
        return value  # Fall back to English on error


def translate_properties(target_lang: str, output_file: str):
    with open(INPUT_FILE, "r", encoding="utf-8") as f:
        lines = f.readlines()

    output_lines = []
    for line in lines:
        stripped = line.strip()

        # Preserve blank lines and comments as-is
        if not stripped or stripped.startswith("#") or stripped.startswith("!"):
            output_lines.append(line)
            continue

        # Parse key = value (supports = and : separators)
        match = re.match(r"^([^=:\s]+)\s*[=:]\s*(.*)", line)
        if match:
            key = match.group(1)
            value = match.group(2).rstrip("\n")
            translated = translate_value(value, target_lang)
            output_lines.append(f"{key}={translated}\n")
        else:
            output_lines.append(line)  # Unknown format — keep as-is

    with open(output_file, "w", encoding="utf-8") as f:
        f.writelines(output_lines)


def wait_for_libretranslate(max_retries: int = 40, interval: int = 15):
    print("⏳ Waiting for LibreTranslate to be ready...")
    for attempt in range(1, max_retries + 1):
        try:
            r = requests.get(f"{API_URL}/languages", timeout=5)
            if r.status_code == 200:
                print(f"✅ LibreTranslate is ready (attempt {attempt})")
                return
        except requests.exceptions.ConnectionError:
            pass
        print(f"   Attempt {attempt}/{max_retries} — retrying in {interval}s...")
        time.sleep(interval)

    print("❌ LibreTranslate did not become ready in time.", file=sys.stderr)
    sys.exit(1)


if __name__ == "__main__":
    wait_for_libretranslate()

    for lang_code, lang_name in LANGUAGES.items():
        output = f"src/main/resources/messages_{lang_code}.properties"
        print(f"\n🌍 Translating to {lang_name} → {output}")
        translate_properties(lang_code, output)
        print(f"   ✅ Done")

    print("\n🎉 All translations complete!")