package com.admin.exceptions

class FeignClientException : RuntimeException {
    constructor(msg: String) : super(msg)
}

class RequestInvalidException : RuntimeException {
    constructor(msg: String) : super(msg)
}
