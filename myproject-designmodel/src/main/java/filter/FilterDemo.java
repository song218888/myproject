package filter;

import java.util.ArrayList;
import java.util.List;

class Person {

	private String name;
	private String gender;
	private String maritalStatus;

	public Person(String name, String gender, String maritalStatus) {
		this.name = name;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}
}

interface Filter {
	public List<Person> filter(List<Person> persons);
}

// 男性
class MaleFilter implements Filter {

	@Override
	public List<Person> filter(List<Person> persons) {
		List<Person> malePersons = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getGender().equalsIgnoreCase("MALE")) {
				malePersons.add(person);
			}
		}
		return malePersons;
	}
}

// 女性
class FemaleFilter implements Filter {

	@Override
	public List<Person> filter(List<Person> persons) {
		List<Person> femalePersons = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getGender().equalsIgnoreCase("FEMALE")) {
				femalePersons.add(person);
			}
		}
		return femalePersons;
	}
}

// 单身
class SingleFilter implements Filter {

	@Override
	public List<Person> filter(List<Person> persons) {
		List<Person> singlePersons = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
				singlePersons.add(person);
			}
		}
		return singlePersons;
	}
}

class AndFilter implements Filter {

	private Filter criteria;
	private Filter otherCriteria;

	public AndFilter(Filter criteria, Filter otherCriteria) {
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
	}

	@Override
	public List<Person> filter(List<Person> persons) {
		List<Person> firstCriteriaPersons = criteria.filter(persons);
		return otherCriteria.filter(firstCriteriaPersons);
	}
}

class OrFilter implements Filter {

	private Filter criteria;
	private Filter otherCriteria;

	public OrFilter(Filter criteria, Filter otherCriteria) {
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
	}

	@Override
	public List<Person> filter(List<Person> persons) {
		List<Person> firstCriteriaItems = criteria.filter(persons);
		List<Person> otherCriteriaItems = otherCriteria.filter(persons);

		for (Person person : otherCriteriaItems) {
			if (!firstCriteriaItems.contains(person)) {
				firstCriteriaItems.add(person);
			}
		}
		return firstCriteriaItems;
	}
}

public class FilterDemo {
	public static void main(String[] args) {
		// 初始化数据
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("霍一", "FEMALE", "MARRIED"));
		persons.add(new Person("邓二", "MALE", "MARRIED"));
		persons.add(new Person("张三", "MALE", "SINGLE"));
		persons.add(new Person("李四", "FEMALE", "MARRIED"));
		persons.add(new Person("王五", "MALE", "SINGLE"));
		persons.add(new Person("赵六", "FEMALE", "SINGLE"));
		persons.add(new Person("孙七", "MALE", "SINGLE"));
		persons.add(new Person("罗八", "MALE", "MARRIED"));
		persons.add(new Person("刘九", "FEMALE", "SINGLE"));
		persons.add(new Person("史十", "FEMALE", "SINGLE"));
		// 打印出所有男性的信息
		System.out.println("---------------------所有男性---------------------");
		List<Person> maleList = new MaleFilter().filter(persons);
		printList(maleList);
		// 打印出所有单身的信息
		System.out.println("---------------------所有单身---------------------");
		List<Person> singleList = new SingleFilter().filter(persons);
		printList(singleList);
		// 打印出所有已婚女性的信息
		System.out.println("--------------------所有单身男性-------------------");
		List<Person> marriedFemaleList = new AndFilter(new SingleFilter(), new MaleFilter()).filter(persons);
		printList(marriedFemaleList);
		// 打印出所有单身或女性的信息
		System.out.println("-------------------所有单身或女性------------------");
		List<Person> singleOrFemaleList = new OrFilter(new SingleFilter(), new FemaleFilter()).filter(persons);
		printList(singleOrFemaleList);
	}

	// 打印列表中的数据信息
	private static void printList(List<Person> list) {
		for (Person person : list) {
			System.out.println(person.toString());
		}
	}
}