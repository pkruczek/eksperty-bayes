package pl.edu.agh.se.run.gui;

import smile.Network;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ComputerDoctor {
    private JCheckBox komputerNieWłączaSięCheckBox;
    private JCheckBox słychaćWentylatoryCheckBox;
    private JCheckBox ekranJestCzarnyCheckBox;
    private JCheckBox widaćEkranBootFailureCheckBox;
    private JCheckBox systemOperacyjnyŁadujeSięCheckBox;
    private JCheckBox komunikatBłęduSystemuOperacyjnegoCheckBox;
    private JCheckBox słychaćSygnałyGłosniczkaSystemowegoCheckBox;
    private JCheckBox słychaćStukCheckBox;
    private JCheckBox widaćNiebieskiEkranTzwCheckBox;
    private JCheckBox pamięćRAMJestNiepoprawnieCheckBox;
    private JCheckBox niektórePlikiZostałyUszkodzoneCheckBox;
    private JButton aktualizujButton;
    private JLabel mothProb;
    private JLabel ramProb;
    private JLabel powSuppProb;
    private JLabel osProb;
    private JLabel hddProb;
    private JPanel myPanel;
    private JLabel gpuProb;

    private Network network;

    private Map<JCheckBox, String> checkBoxes = new HashMap<JCheckBox, String>() {{
        put(komputerNieWłączaSięCheckBox, "nie_wlacza_sie");
        put(słychaćWentylatoryCheckBox, "slychac_wentylatory");
        put(ekranJestCzarnyCheckBox, "czarny_ekran");
        put(widaćEkranBootFailureCheckBox, "ekran__boot_failure");
        put(systemOperacyjnyŁadujeSięCheckBox, "system_sie_laduje");
        put(komunikatBłęduSystemuOperacyjnegoCheckBox, "komunikat_bledu_os");
        put(słychaćSygnałyGłosniczkaSystemowegoCheckBox, "slychac_sygnaly_glosniczka_systemowego");
        put(słychaćStukCheckBox, "slychac_stuk");
        put(widaćNiebieskiEkranTzwCheckBox, "random_blue_screen");
        put(pamięćRAMJestNiepoprawnieCheckBox, "niepoprawne_wyswietlanie_ramu_w_ustawienieach");
        put(niektórePlikiZostałyUszkodzoneCheckBox, "uszkodzone_pliki");
    }};

    private Map<JLabel, String> probabilities = new HashMap<JLabel, String>() {{
        put(mothProb, "Awaria_plyty_glownej");
        put(ramProb, "Awaria_ramu");
        put(powSuppProb, "Awaria_zasilacza");
        put(osProb, "Awaria_systemu_operacyjnego");
        put(hddProb, "Awaria_HDD");
        put(gpuProb, "Awaria_GPU");
    }};

    public ComputerDoctor(Network network) {
        this.network = network;
        aktualizujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                recompute();
            }
        });
    }

    private void recompute() {
        checkBoxes.forEach((checkBox, nodeId) -> {
            String evidenceValue = Option.of(checkBox.isSelected()).value;
            network.setEvidence(nodeId, evidenceValue);
        });
        network.updateBeliefs();
        probabilities.forEach((label, nodeId) -> {
            double prob = network.getNodeValue(nodeId)[0] * 100;
            String answer = String.format("%.1f %%", prob);
            label.setText(answer);
        });
    }

    public static void startGui(Network network) {
        JFrame frame = new JFrame("Computer Doctor");
        frame.setContentPane(new ComputerDoctor(network).myPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private enum Option {
        YES("TAK"), NO("NIE");

        private String value;

        private static Option of(boolean selected) {
            if (selected) {
                return YES;
            } else {
                return NO;
            }
        }

        Option(String value) {
            this.value = value;
        }
    }
}
