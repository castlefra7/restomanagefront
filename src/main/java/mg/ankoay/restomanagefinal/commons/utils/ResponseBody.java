package mg.ankoay.restomanagefinal.commons.utils;

import java.util.ArrayList;

public class ResponseBody<T> {
	private Status status;
	private ArrayList<T> data;

	public ResponseBody() {
		setStatut(new Status());
		setData(new ArrayList<>());
	}

	public Status getStatut() {
		return status;
	}

	public final void setStatut(Status statut) {
		this.status = statut;
	}

	public ArrayList<T> getData() {
		return data;
	}

	public final void setData(ArrayList<T> data) {
		this.data = data;
	}
}
