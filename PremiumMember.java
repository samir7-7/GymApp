public class PremiumMember extends GymMember {
  private final double premiumCharge;
  private String personalTrainer;
  private boolean isFullPayment;
  private double paidAmount;
  private double discountAmount;

  PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB,
      String membershipStartDate, String personalTrainer) {
    super(id, name, location, phone, email, gender, DOB, membershipStartDate);
    premiumCharge = 50000;
    paidAmount = 0;
    isFullPayment = false;
    discountAmount = 0;
  }

  public double getPremiumCharge() {
    return premiumCharge;
  }

  public String getPersonalTrainer() {
    return personalTrainer;
  }

  public boolean getIsFullPayment() {
    return isFullPayment;
  }

  public double getPaidAmount() {
    return paidAmount;
  }

  public double getDiscountAmount() {
    return discountAmount;
  }

  public String payDeuAmount(double paidAmount) {
    if (!isFullPayment) {
      this.paidAmount += paidAmount;
      if (this.paidAmount > premiumCharge) {
        isFullPayment = true;
        return "Full payment done you're getting Rs " + (paidAmount - premiumCharge) + " as change.";
      } else if (this.paidAmount == premiumCharge) {
        isFullPayment = true;
        return "Full payment done.";
      } else {
        return "Amount paid successfully. Your remaining deu is Rs " + (premiumCharge - this.paidAmount);
      }
    } else {
      return "Full payment already done.";
    }
  }

  public void calculateDiscount() {
    if (isFullPayment) {
      discountAmount = premiumCharge * 0.1;
      System.out.println("Consratulations you got 10% discount on full payment.");
    }
  }

  public void revertPremiumMembership() {
    super.resetMembership();
    paidAmount = 0;
    isFullPayment = false;
    discountAmount = 0;
    personalTrainer = "";
  }

  @Override
  public void display() {
    super.display();
    System.out.println("Personal Trainer: " + personalTrainer);
    System.out.println("Paid Amount: " + paidAmount);
    System.out.println("Full Payment: " + isFullPayment);
    System.out.println("Remaining Deu: " + (premiumCharge - paidAmount));
    if (isFullPayment)
      System.out.println("Discount Amount: " + discountAmount);

  }

  @Override
  void markAttendence() {
    attendence++;
    loyaltyPoints += 5;
  }
}
