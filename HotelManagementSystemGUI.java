import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystemGUI {

    private static String[][] rooms = new String[10][2];
    private static JFrame frame = new JFrame("Hotel Management System");
    private static JPanel panel = new JPanel();
    private static JButton checkInButton = new JButton("Check-In");
    private static JButton checkOutButton = new JButton("Check-Out");
    private static JButton viewOccupancyButton = new JButton("View Occupancy");
    private static JTextArea occupancyTextArea = new JTextArea(10, 20);

    public static void main(String[] args) {
        // Initialize hotel rooms
        for (int i = 0; i < rooms.length; i++) {
            rooms[i][0] = String.valueOf(i + 1);
            rooms[i][1] = "Empty";
        }

        // Set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        panel.setLayout(new FlowLayout());

        // Add buttons and text area to panel
        panel.add(checkInButton);
        panel.add(checkOutButton);
        panel.add(viewOccupancyButton);
        occupancyTextArea.setEditable(false);
        panel.add(new JScrollPane(occupancyTextArea));

        // Add action listeners to buttons
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkIn();
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkOut();
            }
        });

        viewOccupancyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewOccupancy();
            }
        });

        // Add panel to frame and set visible
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void checkIn() {
        String roomNumberString = JOptionPane.showInputDialog(frame, "Enter room number:");
        int roomNumber = Integer.parseInt(roomNumberString) - 1;
        if (roomNumber >= 0 && roomNumber < rooms.length && rooms[roomNumber][1].equals("Empty")) {
            String guestName = JOptionPane.showInputDialog(frame, "Enter guest name:");
            rooms[roomNumber][1] = guestName;
            JOptionPane.showMessageDialog(frame, "Check-in successful.");
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid room number or room is occupied.");
        }
    }

    private static void checkOut() {
        String roomNumberString = JOptionPane.showInputDialog(frame, "Enter room number:");
        int roomNumber = Integer.parseInt(roomNumberString) - 1;
        if (roomNumber >= 0 && roomNumber < rooms.length && !rooms[roomNumber][1].equals("Empty")) {
            JOptionPane.showMessageDialog(frame, "Guest " + rooms[roomNumber][1] + " has checked out.");
            rooms[roomNumber][1] = "Empty";
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid room number or room is already vacant.");
        }
    }

    private static void viewOccupancy() {
        StringBuilder occupancy = new StringBuilder();
        for (int i = 0; i < rooms.length; i++) {
            occupancy.append("Room ").append(rooms[i][0]).append(": ").append(rooms[i][1]).append("\n");
        }
        occupancyTextArea.setText(occupancy.toString());
    }
}
