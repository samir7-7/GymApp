public class RegularMember extends GymMember {
  private final int attendenceLimit;
  private boolean isEligibleForUpgrade;
  private String removalReason;
  private String referralSource;
  private String plan;
  private double price;

  RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB,
      String membershipStartDate, int attendenceLimit, String referralSource) {

    super(id, name, location, phone, email, gender, DOB, membershipStartDate);
    this.attendenceLimit = attendenceLimit;
    this.referralSource = referralSource;
    isEligibleForUpgrade = false;
    removalReason = "";
    attendenceLimit = 30;
    plan = "basic";
    price = 6500;
  }

  public int getAttendenceLimit() {
    return attendenceLimit;
  }

  public boolean getIsEligibleForUpgrade() {
    return isEligibleForUpgrade;
  }

  public String getRemovalReason() {
    return removalReason;
  }

  public String getReferralSource() {
    return referralSource;
  }

  public String getPlan() {
    return plan;
  }

  public double getPrice() {
    return price;
  }

  @Override
  void markAttendence() {
    attendence++;
    loyaltyPoints += 5;
  }

  public double getPlanPrice(String plan) {
    switch (plan.toLowerCase()) {
      case "basic":
        return 6500.00;
      case "standard":
        return 10000.00;
      case "deluxe":
        return 18500.00;
      default:
        return -1.0;
    }
  }

  public String upgradePlan(String plan) {
    if (isEligibleForUpgrade && getPlanPrice(plan) > 0) {
      this.plan = plan;
      price = getPlanPrice(plan);
      return "Plan upgraded to " + plan;
    } else {
      return "Not eligible for upgrade";
    }
  }

  public void revertRegularMember(String removalReason) {
    super.resetMembership();
    isEligibleForUpgrade = false;
    plan = "basic";
    price = 6500;
    this.removalReason = removalReason;
  }

  @Override
  public void display() {
    super.display();
    System.out.println("Plan: " + plan);
    System.out.println("Price: " + price);
    if (removalReason != "")
      System.out.println("Removal Reason: " + removalReason);
  }

}
