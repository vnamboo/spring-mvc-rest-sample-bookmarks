package bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.Collection;

/**
 * Created by vnamboo on 15/1/2017.
 */
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {

    Collection<Bookmark> findByAccountUserName(String username);
}
