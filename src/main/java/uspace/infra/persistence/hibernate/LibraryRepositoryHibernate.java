package uspace.infra.persistence.hibernate;

import uspace.domain.Library;
import uspace.domain.LibraryName;
import uspace.domain.LibraryRepository;

public class LibraryRepositoryHibernate implements LibraryRepository {
    @Override
    public Library findByName(LibraryName name) {
        return null;
    }

    @Override
    public void saveOrUpdate(Library library) {

    }
}
