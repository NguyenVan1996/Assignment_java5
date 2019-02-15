package anhvanmobile.util.pagination;

import java.util.Iterator;
import java.util.LinkedList;

public class Pagination {

	private LinkedList<Control> controls;

	private final int DEFAULT_LIMIT = 5;

	public Pagination(int totalPages, int limit, int currentPage) {
		controls = new LinkedList<>();
		if (limit <= DEFAULT_LIMIT)
			limit = DEFAULT_LIMIT;
		paginate(totalPages, limit, currentPage);
	}

	private void paginate(int totalPages, int limit, int currentPage) {
		final int first = 1;
		final int last = totalPages;

		if (totalPages <= limit) {
			paginateLessThanOrEqualsToLimit(first, last, currentPage);

		} else {
			if (currentPage == first) {
				paginateCurrentPageEqualsToFirst(limit, last, currentPage);

			} else if (currentPage == last) {
				paginateCurrentPageEqualsToLast(limit, first, currentPage);

			} else {
				if (currentPage - limit / 2 <= first) {
					paginateCurrentPageGreaterOrEqualsThanFirst(limit, first, last, currentPage);

				} else if (currentPage + limit / 2 >= last) {
					paginateCurrentPageLessOrEqualsThanLast(limit, first, last, currentPage);

				} else {
					paginateWhenCurrentPageAtMiddle(limit, first, last, currentPage);
				}
			}
		}
	}

	private void paginateWhenCurrentPageAtMiddle(int limit, int first, int last, int currentPage) {
		int firstPoint = currentPage - limit / 2;
		int lastPoint = currentPage + limit / 2;
		for (int i = firstPoint; i <= lastPoint; i++) {
			if (i == currentPage)
				controls.add(new Control(String.valueOf(i), i, true));
			else
				controls.add(new Control(String.valueOf(i), i, false));
		}
		appendControlsBothSides(first, last, currentPage);
	}

	private void paginateCurrentPageLessOrEqualsThanLast(int limit, int first, int last, int currentPage) {
		int firstPoint = last - limit;
		for (int i = firstPoint; i <= last; i++) {
			if (i == currentPage)
				controls.add(new Control(String.valueOf(i), i, true));
			else
				controls.add(new Control(String.valueOf(i), i, false));
		}
		appendControlsBothSides(first, last, currentPage);
	}

	private void paginateCurrentPageGreaterOrEqualsThanFirst(int limit, int first, int last, int currentPage) {
		for (int i = first; i <= limit; i++) {
			if (i == currentPage)
				controls.add(new Control(String.valueOf(i), i, true));
			else
				controls.add(new Control(String.valueOf(i), i, false));
		}
		appendControlsBothSides(first, last, currentPage);
	}

	private void paginateCurrentPageEqualsToLast(int limit, int first, int currentPage) {
		int firstPoint = currentPage - limit;
		for (int i = firstPoint; i <= currentPage; i++) {
			if (i == currentPage)
				controls.add(new Control(String.valueOf(i), i, true));
			else
				controls.add(new Control(String.valueOf(i), i, false));
		}
		appendControlsLeftSide(first, currentPage);
	}

	private void paginateCurrentPageEqualsToFirst(int limit, int last, int currentPage) {
		for (int i = currentPage; i <= limit; i++) {
			if (i == currentPage)
				controls.add(new Control(String.valueOf(i), i, true));
			else
				controls.add(new Control(String.valueOf(i), i, false));
		}
		appendControlsRightSide(last, currentPage);
	}

	private void paginateLessThanOrEqualsToLimit(int first, int last, int currentPage) {
		for (int i = first; i <= last; i++) {
			if (i == currentPage)
				controls.add(new Control(String.valueOf(i), i, true));
			else
				controls.add(new Control(String.valueOf(i), i, false));
		}
		if (last > 5)
			if (currentPage == first)
				appendControlsRightSide(last, currentPage);
			else if (currentPage == last)
				appendControlsLeftSide(first, currentPage);
			else
				appendControlsBothSides(first, last, currentPage);

	}

	// -------------------- append controls --------------------

	private void appendControlsLeftSide(int first, int currentPage) {
		controls.addFirst(new Control("Previous", currentPage - 1, false));
		controls.addFirst(new Control("First", first, false));
	}

	private void appendControlsRightSide(int totalPages, int currentPage) {
		controls.addLast(new Control("Next", currentPage + 1, false));
		controls.addLast(new Control("Last", totalPages, false));
	}

	private void appendControlsBothSides(int first, int last, int currentPage) {
		appendControlsLeftSide(first, currentPage);
		appendControlsRightSide(last, currentPage);
	}

	public LinkedList<Control> getControls() {
		return controls;
	}

	@Override
	public String toString() {
		String pagination = "";
		String pages = "";
		Iterator<Control> iterator = controls.iterator();
		while (iterator.hasNext()) {
			Control control = iterator.next();
			String label = control.isActived() ? String.format("[%s]", control.getLabel()) : control.getLabel();
			pagination += String.format("%-10s", label);
			pages += String.format("%-10s", control.getPage());
		}
		return pagination + "\n" + pages;
	}
}
