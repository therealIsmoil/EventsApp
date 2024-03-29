package uz.gita.eventsgita.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.eventsgita.data.local.model.EventsData
import uz.gita.eventsgita.domain.usecase.EventsUseCase
import uz.gita.eventsgita.presentation.viewmodel.AddDialogViewModel
import javax.inject.Inject

@HiltViewModel
class AddDialogViewModelImpl
@Inject constructor(
    private val useCase: EventsUseCase
) : ViewModel(),
    AddDialogViewModel {

    init {
        loadData()
    }

    override val getAllDisableEventsLiveData = MutableLiveData<List<EventsData>>()
    override val closeDialogLiveData = MutableLiveData<Unit>()

    override fun updateEventStateToEnable(eventId: Int) {
        useCase.updateEventStateToEnable(eventId).onEach {
            closeDialogLiveData.value = Unit
        }.launchIn(viewModelScope)
    }

    private fun loadData() {
        useCase.getAllDisableEvents().onEach {
            getAllDisableEventsLiveData.value = it
        }.launchIn(viewModelScope)
    }
}