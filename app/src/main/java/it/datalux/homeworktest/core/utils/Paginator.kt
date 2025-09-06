package it.datalux.homeworktest.core.utils

class Paginator {

    private var page = 0

    val nextPage get() = ++page
    val previousPage get() = --page
    val currentPage get() = page

    fun reset() { page = 0 }
}