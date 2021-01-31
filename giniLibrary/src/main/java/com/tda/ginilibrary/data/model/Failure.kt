package com.tda.ginilibrary.data.model

internal sealed class Failure {
    object NetworkConnection : Failure()
    class ServerError(var errorBodyModel: ErrorBodyModel) : Failure()
    object FeatureFailure : Failure()
}
