package it.datalux.homeworktest.core.utils

sealed class AppAlert(val text: String) {

    object NetworkFailure: AppAlert("A network problem has occurred. Please make sure you have Internet connection")
    object ApiFailure: AppAlert("An error occurred. Please try again later.")
}