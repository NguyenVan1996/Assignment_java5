package anhvanmobile.util.pagination;

public class Control {

	private final String label;

	private final int page;

	private final boolean actived;

	public Control(String label, int page, boolean actived) {
		super();
		this.label = label;
		this.page = page;
		this.actived = actived;
	}

	public int getPage() {
		return page;
	}

	public String getLabel() {
		return label;
	}

	public boolean isActived() {
		return actived;
	}

}
