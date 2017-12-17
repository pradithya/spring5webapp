package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.model.repository.AuthorRepository;
import guru.springframework.spring5webapp.model.repository.BookRepository;
import guru.springframework.spring5webapp.model.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Author jkRowling = new Author("J.K.", "Rowling");
        Publisher bloomsburry = new Publisher("Bloomsbury", "UK");
        Book harryPotter = new Book("Harry Potter and the Goblet of Fire", "9789955081760", bloomsburry);
        jkRowling.getBooks().add(harryPotter);
        harryPotter.getAuthors().add(jkRowling);


        Author jrrTolkien = new Author("J.R.R.", "Tolkien");
        Publisher allenAndUnwin = new Publisher("Allen & Unwin", "Australia");
        Book lotr = new Book("Lord of the Ring", "9788373191723", allenAndUnwin);
        jrrTolkien.getBooks().add(lotr);
        lotr.getAuthors().add(jrrTolkien);

        publisherRepository.save(bloomsburry);
        publisherRepository.save(allenAndUnwin);
        authorRepository.save(jkRowling);
        authorRepository.save(jrrTolkien);
        bookRepository.save(harryPotter);
        bookRepository.save(lotr);
    }
}
