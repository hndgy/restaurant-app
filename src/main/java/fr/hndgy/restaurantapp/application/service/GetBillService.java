package fr.hndgy.restaurantapp.application.service;

import javax.transaction.Transactional;

import fr.hndgy.restaurantapp.application.port.out.LoadOrderPort;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class GetBillService {
    private final LoadOrderPort loadOrderPort;

    
}
