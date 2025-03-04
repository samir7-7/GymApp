public abstract class GymMember {
  protected int id;
  protected int attendence;
  protected String name, location, phone, email, gender, DOB, membershipStartDate;
  protected double loyaltyPoints;
  protected boolean activeStatus;

  GymMember(int id, String name, String location, String phone, String email, String gender, String DOB,
      String membershipStartDate) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.phone = phone;
    this.email = email;
    this.gender = gender;
    this.DOB = DOB;
    this.membershipStartDate = membershipStartDate;
  }

  public int getId() {
    return id;
  }

  public int getAttendence() {
    return attendence;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getGender() {
    return gender;
  }

  public String getDOB() {
    return DOB;
  }

  public String getMembershipStartDate() {
    return membershipStartDate;
  }

  public double getLoyaltyPoints() {
    return loyaltyPoints;
  }

  public boolean isActiveStatus() {
    return activeStatus;
  }

  abstract void markAttendence();

  public void activateMembership() {
    activeStatus = true;
  }

  public void deactivateMembership() {
    activeStatus = false;
  }

  public void resetMembership() {
    attendence = 0;
    loyaltyPoints = 0;
    activeStatus = false;
  }

  public void display() {
    System.out.println("ID: " + id);
    System.out.println("Name: " + name);
    System.out.println("Location: " + location);
    System.out.println("Phone: " + phone);
    System.out.println("Email: " + email);
    System.out.println("Gender: " + gender);
    System.out.println("DOB: " + DOB);
    System.out.println("Membership Start Date: " + membershipStartDate);
    System.out.println("Loyalty Points: " + loyaltyPoints);
    System.out.println("Active Status: " + activeStatus);
  }

}