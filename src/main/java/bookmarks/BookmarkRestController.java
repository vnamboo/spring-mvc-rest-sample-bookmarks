package bookmarks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * Created by vnamboo on 15/1/2017.
 */
@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkRestController {

    private final BookmarkRepository bookmarkRepository;
    private final AccountRepository accountRepository;

    @Autowired
    BookmarkRestController(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Bookmark> readBookmarks(@PathVariable String userId){
        this.validateUser(userId);
        return this.bookmarkRepository.findByAccountUserName(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark in){
        this.validateUser(userId);
        return this.accountRepository
                    .findByUserName(userId)
                    .map(account -> {
                        Bookmark result=bookmarkRepository.save(new Bookmark(account,in.getUri(),in.getDescription()));
                        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                                  .buildAndExpand(result.getId()).toUri();
                        return ResponseEntity.created(location).build();
                    })
                    .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{bookmarkId}")
    public Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId){
        this.validateUser(userId);
        return this.bookmarkRepository.findOne(bookmarkId);
    }

    private void validateUser(String userId) {
        this.accountRepository.findByUserName(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }


}
