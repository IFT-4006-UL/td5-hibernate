package infra.persistence.hibernate;

import domain.Library;
import domain.LibraryName;
import domain.LibraryRepository;

public class LibraryRepositoryHibernate implements LibraryRepository {
    @Override
    public Library findByName(LibraryName name) {
        return null;
    }

    @Override
    public void saveOrUpdate(Library library) {

    }
}
