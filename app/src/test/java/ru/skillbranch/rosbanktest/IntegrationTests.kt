package ru.skillbranch.rosbanktest
import io.mockk.*
import org.junit.Test
import ru.skillbranch.rosbanktest.presenter.CurrencyPresenter
import ru.skillbranch.rosbanktest.view.RecyclerFragment

class IntegrationTests {
    @Test
    fun CurrencyPresenterIntegrationTest(){
        val presenter = mockk<CurrencyPresenter>()
        val view = spyk<RecyclerFragment>()
        every{view.clickEditText("134")} returns  presenter.recalculationSum("134")
        view.clickEditText("134")
        verify { presenter.recalculationSum("134") }
        confirmVerified(view)
    }
}