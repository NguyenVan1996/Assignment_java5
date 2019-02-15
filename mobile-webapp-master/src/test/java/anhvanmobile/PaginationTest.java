package anhvanmobile;

import java.util.Scanner;

public class PaginationTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Active page: ");
			int active = Integer.parseInt(scanner.nextLine());
			if (active == 0)
				break;
			paginate(active);
			System.out.println();
		}
		scanner.close();
	}

	public static void paginate(int active) {
		int totalPages = 10;
		int limit = 10;
		int first = 1;
		int last = totalPages;

		if (totalPages <= limit) {
			for (int i = 1; i <= totalPages; i++) {
				if (i == active)
					System.out.print(String.format("[%d] ", i));
				else
					System.out.print(i + " ");
			}
		} else {
			if (active == first) {
				for (int i = active; i <= limit + 1; i++) {
					if (i == active)
						System.out.print(String.format("[%d] ", i));
					else if (i == limit + 1)
						System.out.print("last");
					else
						System.out.print(i + " ");
				}
			} else if (active == last) {
				int firstPoint = active - 10;
				for (int i = firstPoint; i <= active; i++) {
					if (i == active)
						System.out.print(String.format("[%d] ", i));
					else
						System.out.print(i + " ");
				}
			} else {
				if (active - 4 <= first) {
					for (int i = 1; i <= limit + 1; i++) {
						if (i == active)
							System.out.print(String.format("[%d] ", i));
						else
							System.out.print(i + " ");
					}
				} else if (active + 4 >= last) {
					int firstPoint = last - 10;
					for (int i = firstPoint; i <= last; i++) {
						if (i == active)
							System.out.print(String.format("[%d] ", i));
						else
							System.out.print(i + " ");
					}
				} else {
					int firstPoint = active - 5;
					int lastPoint = active + 5;
					for (int i = firstPoint; i <= lastPoint; i++) {
						if (i == active)
							System.out.print(String.format("[%d] ", i));
						else
							System.out.print(i + " ");
					}
				}
			}
		}
	}
}
