

//変数宣言をする
public class Client {
	public static void main(String[] args) {
		Koujyou koujyou1 = new TvKoujyou();
		Koujyou koujyou2 = new RadioKoujyou();
		Seihin[] array = new Seihin[3];
		array[0] = koujyou1.create();
		array[1] = koujyou2.create();
		array[2] = koujyou1.create();
		for (int i = 0; i < array.length; ++i) { array[i].print(); }
		}
	}

//製品の登録
abstract class Koujyou {
	public final Seihin create() {
		Seihin seihin = factoryMethod();
		touroku(seihin);
		return seihin;
		}
	public abstract Seihin factoryMethod();
public abstract void touroku(Seihin s);
}

//工場で作られた新しいテレビに製品番号を付ける
class TvKoujyou extends Koujyou {
	public Seihin factoryMethod() {
		return new Television();
		}
	public void touroku(Seihin s) {
		Television t = (Television) s;
		t.numberring();
		t.setDate(Date.today());
		}
	}

//工場で作られた新しいラジオに製品番号を付ける
class RadioKoujyou extends Koujyou {
	public Seihin factoryMethod() {
		return new Radio();
		}
	public void touroku(Seihin s) {
		Radio r = (Radio) s;
		r.numberring();
		}
	}

//製品を持つクラス
abstract class Seihin {
	public abstract void print();
	}

//テレビの情報をもってきて表示している
class Television extends Seihin {
	private int tvSerialNumber;
	private String date;
	public void numberring() {
		tvSerialNumber = Counter.getTvNumber();
		}
	public void setDate(String date) {
		this.date = date;
		}
	public void print() {
		System.out.println("このテレビに関する情報:");
		System.out.println(" 製造番号:" + tvSerialNumber);
		System.out.println(" 製造年月日:" + date);
		}
	}

//ラジオの情報を持ってきて表示している
class Radio extends Seihin {
	private int radioSerialNumber;
	public void numberring() {
		radioSerialNumber = Counter.getRadioNumber();
		}
	public void print() {
		System.out.println("このラジオに関する情報:");
		System.out.println(" 製造番号:" + radioSerialNumber);
		}
	}

//今日の日付
class Date {
	public static String today() {
		return "2004/01/20";
		}
	}

//ラジオ・テレビの製品番号のカウントを1あげる
class Counter {
	private static int tvNum = 100;
	private static int radioNum = 76;
	public static int getTvNumber() {
		return tvNum++;
		}
	public static int getRadioNumber() {
		return radioNum++;
		}
	}





