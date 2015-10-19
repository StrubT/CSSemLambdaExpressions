package ch.bfh.cssem.lambdaexpressions;

public interface NAryInterface<I, O> {

	O execute(I... params);
}
