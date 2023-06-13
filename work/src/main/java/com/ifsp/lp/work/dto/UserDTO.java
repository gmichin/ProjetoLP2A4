package com.ifsp.lp.work.dto;
import java.util.UUID;
public class UserDTO {
	private UUID  id;
    private String gender;
    private NameDTO name;
    private LocationDTO location;
    private String email;
    private DobDTO dob;



	private RegisteredDTO registered;
    private String phone;
    private String cell;
    private PictureDTO picture;

	private String region;

	private String userType;

	public UserDTO() {
		this.id = UUID.randomUUID();
	}
// Getters e Setters


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public NameDTO getName() {
		return name;
	}

	public void setName(NameDTO name) {
		this.name = name;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DobDTO getDob() {
		return dob;
	}

	public void setDob(DobDTO dob) {
		this.dob = dob;
	}

	public RegisteredDTO getRegistered() {
		return registered;
	}

	public void setRegistered(RegisteredDTO registered) {
		this.registered = registered;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public PictureDTO getPicture() {
		return picture;
	}

	public void setPicture(PictureDTO picture) {
		this.picture = picture;
	}

	public UUID getId() {
		return id;
	}

	public static class NameDTO {
        public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getFirst() {
			return first;
		}
		public void setFirst(String first) {
			this.first = first;
		}
		public String getLast() {
			return last;
		}
		public void setLast(String last) {
			this.last = last;
		}
		private String title;
        private String first;
        private String last;

        // Getters e Setters
    }

    public static class LocationDTO {
        private String street;
        private String city;
        private String state;
        private int postcode;
        private CoordinatesDTO coordinates;
        private TimezoneDTO timezone;
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public int getPostcode() {
			return postcode;
		}
		public void setPostcode(int postcode) {
			this.postcode = postcode;
		}
		public CoordinatesDTO getCoordinates() {
			return coordinates;
		}
		public void setCoordinates(CoordinatesDTO coordinates) {
			this.coordinates = coordinates;
		}
		public TimezoneDTO getTimezone() {
			return timezone;
		}
		public void setTimezone(TimezoneDTO timezone) {
			this.timezone = timezone;
		}

        // Getters e Setters
    }

    public static class CoordinatesDTO {
        private String latitude;
        private String longitude;
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

        // Getters e Setters
    }

    public static class TimezoneDTO {
        private String offset;
        private String description;
		public String getOffset() {
			return offset;
		}
		public void setOffset(String offset) {
			this.offset = offset;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

        // Getters e Setters
    }

    public static class DobDTO {
        private String date;
        private int age;
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}

        // Getters e Setters
    }

    public static class RegisteredDTO {
        private String date;
        private int age;
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}

        // Getters e Setters
    }

    public static class PictureDTO {
        private String large;
        private String medium;
        private String thumbnail;
		public String getLarge() {
			return large;
		}
		public void setLarge(String large) {
			this.large = large;
		}
		public String getMedium() {
			return medium;
		}
		public void setMedium(String medium) {
			this.medium = medium;
		}
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}

        // Getters e Setters
    }
}
