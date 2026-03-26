package tinnova.test.com.example.demo.application.usecases;

public interface UseCase<I, O> {
    
    O execute(I input);
}
