import java.awt.print.Book;

interface Iterator {
	public void first(); // ���o���ʒu���ŏ��̗v�f�֕ς���
	public void next(); // ���o���ʒu�����̗v�f�֕ς���
	public boolean isDone(); // ���o���ʒu���Ō�𒴂������H
	public Object getItem(); // ���݂̎��o���ʒu������o��
}

class BookListIterator implements Iterator {
	private BookListAggregate aggregate;
	private int current;
	public BookListIterator(BookAggregate aggregate) {
		this.aggregate = aggregate;
		}
	@Override
	public void first() {
		current = 0;
		}
	@Override
	public void next() {
		current += 1;
		}
	@Override
	public boolean isDone() {
		if (current >= aggregate.getNumberOfStock()) {
			return true;
			}
		else {
			return false;
			}
		}
	@Override
	public Object getItem() {
		return aggregate.getAt(current);
		}
	}

interface Aggregate {
	public Iterator createIterator();
	}

class BookListAggregate implements Aggregate {
	private Book[] list = new Book[20];
	private int numberOfStock;
	@Override
	public Iterator createIterator() {
		return new BookListIterator(this);
		}
	public void add(Book book) {
		list[numberOfStock] = book;
		numberOfStock += 1;
	}
	public Object getAt(int number) {
		return list[number];
	}
	public int getNumberOfStock() {
		return numberOfStock;
	}
}

public class IteratorSample1 {
	public static void main(String[] args) {
		BookListAggregate bookListAggregate = new BookListAggregate();
		Iterator iterator = bookListAggregate.createIterator();
		bookListAggregate.add(new Book("�݂�ȂŃS���t", 3700));
		bookListAggregate.add(new Book("�t�@�C�i���t�@���^�W�A", 7300));
		bookListAggregate.add(new Book("���P�b�g�����X�^�[", 5400));
		bookListAggregate.add(new Book("�T�C�R���̒B�l", 5200));
		iterator.first(); // �܂��T���ꏊ��擪�ʒu�ɂ��Ă��炤
		while ( ! iterator.isDone() ) { // �܂�����H �܂������I
			Book book = (Book)iterator.getItem(); // �͂��ǂ��� (�Ǝ���)
			System.out.println(Book.getName());
			iterator.next(); // ���𒸑�
		}
	}
}

