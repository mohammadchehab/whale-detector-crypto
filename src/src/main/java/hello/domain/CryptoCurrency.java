package hello.domain;

import org.springframework.data.annotation.Id;

public class CryptoCurrency {

	@Id
	private String _id;
	private String _name;

	public CryptoCurrency () {}

	public CryptoCurrency(String key, String name) {
		this._id = key;
		this._name = name;
	}

	public String getId() {
		return _id;
	}

	public void setId(String _key) {
		this._id = _key;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}
}