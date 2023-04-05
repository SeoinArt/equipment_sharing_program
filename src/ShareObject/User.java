package ShareObject;

public class User {
	
		private String id;
		private String passwd;
		private String name;
		private String addr;
		private String tel;
		 
		public void setId(String id) {
			this.id = id;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		
		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getPasswd() {
			return passwd;
		}
		public String getAddr() {
			return addr;
		}
		public String getTel() {
			return tel;
		}
		public String[] getAll() {
			String[] str = {getId(),getName(),getTel()};
			return str;
			
		}
}
