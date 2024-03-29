package uz.gita.eventsgita.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.eventsgita.data.local.model.EventsData
import uz.gita.eventsgita.domain.repository.AppRepository
import uz.gita.eventsgita.domain.usecase.EventsUseCase
import javax.inject.Inject

class EventsUseCaseImpl
@Inject constructor(
    private val repository: AppRepository
) : EventsUseCase {
    private var list: List<EventsData> = ArrayList()

    override fun getAllDisableEvents() = flow<List<EventsData>> {
        val result = repository.getAllDisableEvents().map {
            it.eventsEntityToEventsData()
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getAllEnableEvents() = flow<List<EventsData>> {
        val result = repository.getAllEnableEvents().map {
            it.eventsEntityToEventsData()
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun updateEventStateToDisable(eventId: Int) = flow<Unit> {
        repository.updateEventStateToDisable(eventId)
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override fun updateEventStateToEnable(eventId: Int) = flow<Unit> {
        repository.updateEventStateToEnable(eventId)
        emit(Unit)
    }.flowOn(Dispatchers.IO)

}


