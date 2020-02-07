import java.awt.print.Book;

interface Iterator {
	public void first(); // 取り出し位置を最初の要素へ変える
	public void next(); // 取り出し位置を次の要素へ変える
	public boolean isDone(); // 取り出し位置が最後を超えたか？
	public Object getItem(); // 現在の取り出し位置から取り出す
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
		bookListAggregate.add(new Book("みんなでゴルフ", 3700));
		bookListAggregate.add(new Book("ファイナルファンタジア", 7300));
		bookListAggregate.add(new Book("ロケットモンスター", 5400));
		bookListAggregate.add(new Book("サイコロの達人", 5200));
		iterator.first(); // まず探す場所を先頭位置にしてもらう
		while ( ! iterator.isDone() ) { // まだある？ まだあるよ！
			Book book = (Book)iterator.getItem(); // はいどうぞ (と受取る)
			System.out.println(Book.getName());
			iterator.next(); // 次を頂戴
		}
	}
}

