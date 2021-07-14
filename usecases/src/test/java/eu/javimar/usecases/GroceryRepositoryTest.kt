package eu.javimar.usecases

import com.nhaarman.mockitokotlin2.whenever
import eu.javimar.data.repository.GroceryRepository
import eu.javimar.data.mockedGrocery
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GroceryRepositoryTest {

    @Mock
    lateinit var groceryRepository: GroceryRepository

    lateinit var getGroceryList: GetGroceryList

    @Before
    fun setUp() {
        getGroceryList = GetGroceryList(groceryRepository)
    }

    @Test
    fun `invoke calls grocery repository`() {
        runBlocking {

            val groceries = listOf(mockedGrocery.copy(id = 1))
            whenever(groceryRepository.getGroceryList()).thenReturn(groceries)

            val result = getGroceryList.invoke()

            Assert.assertEquals(groceries, result)
        }
    }
}