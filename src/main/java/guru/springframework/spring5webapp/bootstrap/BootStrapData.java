package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher p1 = new Publisher("CoolLlama", "Migowska 23", "Gdansk", "Pomo", "80-287");
        publisherRepository.save(p1);
        Author lalit = new Author("Lalit", "Chilka");

        Book b1 = new Book("maka paka", "123456");

        lalit.getBooks().add(b1);
        b1.getAuthors().add(lalit);
        b1.setPublisher(p1);
        p1.getBooks().add(b1);

        authorRepository.save(lalit);
        bookRepository.save(b1);
        publisherRepository.save(p1);

        Author asu = new Author("Asia", "Chilka");
        Book alpachicos = new Book("Life of alpachicos", "456899");
        asu.getBooks().add(alpachicos);
        alpachicos.getAuthors().add(asu);
        alpachicos.setPublisher(p1);
        p1.getBooks().add(alpachicos);

        authorRepository.save(asu);
        bookRepository.save(alpachicos);
        publisherRepository.save(p1);

        System.out.println("Started in bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
        System.out.println("Number of books published: " + p1.getBooks().size());
    }
}
