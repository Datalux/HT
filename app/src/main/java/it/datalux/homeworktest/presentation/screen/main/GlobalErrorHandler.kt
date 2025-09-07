package it.datalux.homeworktest.presentation.screen.main

import it.datalux.homeworktest.core.utils.AppAlert
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalErrorHandler @Inject constructor() {

    private val _errorEvents = Channel<AppAlert>(Channel.BUFFERED)
    val errorEvents: ReceiveChannel<AppAlert> = _errorEvents

    /**
     * Sends a new error event to be displayed.
     * This method is suspend because sending to a channel can suspend if the buffer is full.
     */
    suspend fun notify(error: AppAlert) {
        _errorEvents.send(error)
    }

}