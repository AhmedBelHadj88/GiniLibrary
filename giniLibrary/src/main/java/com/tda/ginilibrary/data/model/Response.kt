package com.tda.ginilibrary.data.model

internal sealed class Response {
    class SUCCESS(var data: Any) : Response()
    class FAILURE(var failure: Failure) : Response()
}