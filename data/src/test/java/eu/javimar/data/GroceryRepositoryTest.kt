package eu.javimar.data

import eu.javimar.data.repository.GroceryRepository
import eu.javimar.data.source.LocalDataSource
import eu.javimar.data.source.RemoteDataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GroceryRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var groceryRepository: GroceryRepository

    @Before
    fun setUp() {
        groceryRepository = GroceryRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `getGroceries gets from local data source first`() {
        runBlocking {

            val localGroceries = listOf(mockedGrocery.copy(1))
            whenever(localDataSource.isGroceryListEmpty()).thenReturn(false)
            whenever(localDataSource.getAllGroceries()).thenReturn(localGroceries)

            val result = groceryRepository.getGroceryList()

            assertEquals(localGroceries, result)
        }
    }

    @Test
    fun `getGroceries saves remote data to local`() {
        runBlocking {

            val remoteGroceries = listOf(mockedGrocery.copy(2))
            whenever(localDataSource.isGroceryListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getGroceryList()).thenReturn(remoteGroceries)

            groceryRepository.getGroceryList()

            verify(localDataSource).saveGroceries(remoteGroceries)
        }
    }

    @Test
    fun `increase quantity updates local data source`() {
        runBlocking {

            val grocery = mockedGrocery.copy(id = 1)

            groceryRepository.addItem(grocery.id)

            verify(localDataSource).increaseCartQuantity(grocery.id)
        }
    }
}