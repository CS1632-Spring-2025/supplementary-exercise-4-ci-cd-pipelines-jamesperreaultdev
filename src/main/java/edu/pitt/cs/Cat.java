package edu.pitt.cs;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

public interface Cat {
	public static Cat createInstance(InstanceType type, int id, String name) {
		switch (type) {
			case IMPL:
				return new CatImpl(id, name);
			case BUGGY:
				return new CatBuggy(id, name);
			case SOLUTION:
				return new CatSolution(id, name);
			case MOCK:
				 Cat mockCat = Mockito.mock(Cat.class);

				 Mockito.when(mockCat.getName()).thenReturn(name);
				 Mockito.when(mockCat.getId()).thenReturn(id);
				 Mockito.when(mockCat.getRented()).thenReturn(false);
				 doNothing().when(mockCat).renameCat(anyString());
				 Mockito.when(mockCat.toString()).thenReturn("ID " + id + ". " + name);





			    // TODO: Return a mock object that emulates the behavior of the real object, if you feel you need one.
				return mockCat;
			default:
				assert(false);
				return null;
		}
	}

	// WARNING: You are not allowed to change any part of the interface.
	// That means you cannot add any method nor modify any of these methods.
	
	public void rentCat();

	public void returnCat();

	public void renameCat(String name);

	public String getName();

	public int getId();

	public boolean getRented();

	public String toString();
}
