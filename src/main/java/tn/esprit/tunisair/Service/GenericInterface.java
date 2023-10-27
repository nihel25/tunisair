package tn.esprit.tunisair.Service;

import java.util.List;

public interface GenericInterface <T>{




    List<T> lister();




    void delete(Long id);



}
