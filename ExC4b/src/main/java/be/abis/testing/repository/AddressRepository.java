package be.abis.testing.repository;


import be.abis.testing.model.Address;

import java.io.IOException;
import java.nio.file.Path;

public interface AddressRepository {
    
    void saveAddress(Address a, Path pathToFile) throws IOException;

}
