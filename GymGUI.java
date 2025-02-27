import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GymGUI extends JFrame {
  private JTextField idField, nameField, locationField, phoneField, emailField, dobField, startDateField,
      referralSourceField, paidAmountField, removalReasonField, trainerField;
  private JRadioButton maleRadioButton, femaleRadioButton;
  private JComboBox<String> planComboBox;
  private JButton addRegularMemberButton, addPremiumMemberButton, activateMembershipButton, deactivateMembershipButton,
      markAttendanceButton, upgradePlanButton, calculateDiscountButton, revertRegularMemberButton,
      revertPremiumMemberButton, payDueAmountButton, displayButton, clearButton, saveToFileButton, readFromFileButton;
  private JTextArea displayArea;

  private final Color BUTTON_COLOR = new Color(42, 103, 164);

  private ArrayList<GymMember> allMembers = new ArrayList<>();

  public boolean isValidID(String id) {
    try {
      int idInt = Integer.parseInt(id);
      for (int i = 0; i < allMembers.size(); i++) {
        if (allMembers.get(i).getId() == idInt) {
          return false;
        }
      }
      return idInt > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public GymGUI() {
    setTitle("Gym Management System");
    setSize(1000, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Create input panel
    JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));
    inputPanel.setBorder(BorderFactory.createTitledBorder("Member Details"));

    // Add padding to text fields
    idField = createTextFieldWithPadding();
    nameField = createTextFieldWithPadding();
    locationField = createTextFieldWithPadding();
    phoneField = createTextFieldWithPadding();
    emailField = createTextFieldWithPadding();
    dobField = createTextFieldWithPadding();
    startDateField = createTextFieldWithPadding();
    referralSourceField = createTextFieldWithPadding();
    paidAmountField = createTextFieldWithPadding();
    removalReasonField = createTextFieldWithPadding();
    trainerField = createTextFieldWithPadding();

    maleRadioButton = new JRadioButton("Male");
    femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();
    genderGroup.add(maleRadioButton);
    genderGroup.add(femaleRadioButton);

    planComboBox = new JComboBox<>(new String[] { "Basic", "Standard", "Deluxe" });

    inputPanel.add(new JLabel("ID:"));
    inputPanel.add(idField);
    inputPanel.add(new JLabel("Name:"));
    inputPanel.add(nameField);
    inputPanel.add(new JLabel("Location:"));
    inputPanel.add(locationField);
    inputPanel.add(new JLabel("Phone:"));
    inputPanel.add(phoneField);
    inputPanel.add(new JLabel("Email:"));
    inputPanel.add(emailField);
    inputPanel.add(new JLabel("Gender:"));
    JPanel genderPanel = new JPanel();
    genderPanel.add(maleRadioButton);
    genderPanel.add(femaleRadioButton);
    inputPanel.add(genderPanel);
    inputPanel.add(new JLabel("DOB:"));
    inputPanel.add(dobField);
    inputPanel.add(new JLabel("Membership Start Date:"));
    inputPanel.add(startDateField);
    inputPanel.add(new JLabel("Referral Source:"));
    inputPanel.add(referralSourceField);
    inputPanel.add(new JLabel("Paid Amount:"));
    inputPanel.add(paidAmountField);
    inputPanel.add(new JLabel("Removal Reason:"));
    inputPanel.add(removalReasonField);
    inputPanel.add(new JLabel("Trainer's Name:"));
    inputPanel.add(trainerField);
    inputPanel.add(new JLabel("Plan:"));
    inputPanel.add(planComboBox);

    // Create button panel
    JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 5, 5));
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

    // Create buttons with the specified color #178BFF
    addRegularMemberButton = createButton("Add Regular Member", BUTTON_COLOR);
    addPremiumMemberButton = createButton("Add Premium Member", BUTTON_COLOR);
    activateMembershipButton = createButton("Activate Membership", BUTTON_COLOR);
    deactivateMembershipButton = createButton("Deactivate Membership", BUTTON_COLOR);
    markAttendanceButton = createButton("Mark Attendance", BUTTON_COLOR);
    upgradePlanButton = createButton("Upgrade Plan", BUTTON_COLOR);
    calculateDiscountButton = createButton("Calculate Discount", BUTTON_COLOR);
    revertRegularMemberButton = createButton("Revert Regular Member", BUTTON_COLOR);
    revertPremiumMemberButton = createButton("Revert Premium Member", BUTTON_COLOR);
    payDueAmountButton = createButton("Pay Due Amount", BUTTON_COLOR);
    displayButton = createButton("Display", BUTTON_COLOR);
    clearButton = createButton("Clear", BUTTON_COLOR);
    saveToFileButton = createButton("Save to File", BUTTON_COLOR);
    readFromFileButton = createButton("Read from File", BUTTON_COLOR);

    buttonPanel.add(addRegularMemberButton);
    buttonPanel.add(addPremiumMemberButton);
    buttonPanel.add(activateMembershipButton);
    buttonPanel.add(deactivateMembershipButton);
    buttonPanel.add(markAttendanceButton);
    buttonPanel.add(upgradePlanButton);
    buttonPanel.add(calculateDiscountButton);
    buttonPanel.add(revertRegularMemberButton);
    buttonPanel.add(revertPremiumMemberButton);
    buttonPanel.add(payDueAmountButton);
    buttonPanel.add(displayButton);
    buttonPanel.add(clearButton);
    buttonPanel.add(saveToFileButton);
    buttonPanel.add(readFromFileButton);

    // Create display area
    displayArea = new JTextArea();
    displayArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(displayArea);

    // Add components to the frame
    add(inputPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.CENTER);
    add(scrollPane, BorderLayout.SOUTH);

    addRegularMemberButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String id = idField.getText();
        String name = nameField.getText();
        String location = locationField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";
        String dob = dobField.getText();
        String startDate = startDateField.getText();

        if (id.isEmpty() || name.isEmpty() || location.isEmpty() || phone.isEmpty() || email.isEmpty() || dob.isEmpty()
            || startDate.isEmpty()) {
          JOptionPane.showMessageDialog(GymGUI.this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

          if (isValidID(id)) {
            RegularMember regularMember = new RegularMember(Integer.parseInt(id), name, location, phone, email,
                gender,
                dob, startDate, 30,
                referralSourceField.getText());
            allMembers.add(regularMember);
            JOptionPane.showMessageDialog(GymGUI.this, "Data Added Successfully");
          } else {
            JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

    addPremiumMemberButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    activateMembershipButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    deactivateMembershipButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    markAttendanceButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    upgradePlanButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    calculateDiscountButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    revertRegularMemberButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    revertPremiumMemberButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    payDueAmountButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    displayButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
        System.out.println(idField.getText());

      }
    });

    clearButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Clear all fields
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        dobField.setText("");
        startDateField.setText("");
        referralSourceField.setText("");
        paidAmountField.setText("");
        removalReasonField.setText("");
        trainerField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        planComboBox.setSelectedIndex(0);
      }
    });

    saveToFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });

    readFromFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Implement backend logic here
      }
    });
  }

  // Helper method to create a text field with padding
  private JTextField createTextFieldWithPadding() {
    JTextField textField = new JTextField();
    textField.setBorder(BorderFactory.createCompoundBorder(
        textField.getBorder(),
        BorderFactory.createEmptyBorder(5, 10, 5, 10) // Top, left, bottom, right padding
    ));
    return textField;
  }

  // Helper method to create a button with a specific color
  private JButton createButton(String text, Color color) {
    JButton button = new JButton(text);
    button.setBackground(color);
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    return button;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new GymGUI().setVisible(true);
      }
    });
  }
}