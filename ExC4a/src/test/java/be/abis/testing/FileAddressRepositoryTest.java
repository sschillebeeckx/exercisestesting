package be.abis.testing;



import be.abis.testing.model.Address;
import be.abis.testing.repository.AddressRepository;
import be.abis.testing.repository.FileAddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FileAddressRepositoryTest {

    AddressRepository addressRepository;
    @Mock
    Address address;

    @Before
    public void setUp(){
       addressRepository = new FileAddressRepository();
    }

    @Test
    public void repoSavesAddressCorrectly() throws IOException {
        doNothing().when(address).writeToFile(any(Path.class));
        addressRepository.saveAddress(address, Paths.get("somefile1.txt"));
        verify(address).writeToFile(any(Path.class));
    }

    @Test(expected=IOException.class)
    public void repoThrowsIOExceptionWhileSaving() throws IOException {
        doThrow(IOException.class).when(address).writeToFile(any(Path.class));
        addressRepository.saveAddress(address,Paths.get("somefile.txt"));
    }

    @Test
    public void repoSavesAddressCorrectlyWithAnswer() throws IOException {
        Answer myAnswer = new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Path p = invocation.getArgument(0);
                System.out.println("The address was saved to " + p);
                return null;
            }
        };
        /*Answer myAnswer =
                invocation -> {System.out.println("The address was saved to " + invocation.getArgument(0));return null;};*/
        doAnswer(myAnswer).when(address).writeToFile(any(Path.class));
        addressRepository.saveAddress(address, Paths.get("somefile2.txt"));
        verify(address).writeToFile(any(Path.class));
    }

    @Test
    public void repoSavesAddressCorrectlyUseCaptor() throws IOException {
        Path path = Paths.get("somefile3.txt");
        ArgumentCaptor<Path> pathCaptor = ArgumentCaptor.forClass(Path.class);
        addressRepository.saveAddress(address,path);
        verify(address).writeToFile(pathCaptor.capture());
        Path usedPath= pathCaptor.getValue();
        assertEquals("somefile3.txt",usedPath.getFileName().toString());
    }


}