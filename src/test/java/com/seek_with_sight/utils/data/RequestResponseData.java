package com.seek_with_sight.utils.data;

public record RequestResponseData<Req, Res>(Req request, Res response) {
}
