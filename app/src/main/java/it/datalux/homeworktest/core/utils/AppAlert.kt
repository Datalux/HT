package it.datalux.homeworktest.core.utils

sealed class AppAlert(val text: String) {

    object NetworkFailure: AppAlert("Si è verificato un problema di rete. Assicurati di essere connesso ad Internet.")
    object Idle: AppAlert("")
}