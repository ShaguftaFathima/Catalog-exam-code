
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ShamirSecretSharing {

    // Function to decode y-values from different bases
    public static BigInteger decodeValue(String value, int base) {
        return new BigInteger(value, base);
    }

    // Lagrange interpolation to find the constant term (c)
    public static BigInteger lagrangeInterpolation(List<Map.Entry<Integer, BigInteger>> points, int k) {
        BigInteger constantTerm = BigInteger.ZERO;

        for (int i = 0; i < k; i++) {
            BigInteger term = points.get(i).getValue();
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    int x_i = points.get(i).getKey();
                    int x_j = points.get(j).getKey();

                    term = term.multiply(BigInteger.valueOf(x_j))
                            .divide(BigInteger.valueOf(x_j - x_i));
                }
            }
            constantTerm = constantTerm.add(term);
        }

        return constantTerm;
    }

    public static void main(String[] args) {
        try {
            // Load JSON input from file
            FileReader fileReader = new FileReader("input.json");
            JsonObject input = JsonParser.parseReader(fileReader).getAsJsonObject();

            int n = input.getAsJsonObject("keys").get("n").getAsInt();
            int k = input.getAsJsonObject("keys").get("k").getAsInt();

            List<Map.Entry<Integer, BigInteger>> points = new ArrayList<>();

            // Decode points from JSON
            for (Map.Entry<String, JsonElement> entry : input.entrySet()) {
                if (entry.getKey().equals("keys")) continue;

                int x = Integer.parseInt(entry.getKey());
                JsonObject root = entry.getValue().getAsJsonObject();
                int base = root.get("base").getAsInt();
                String encodedY = root.get("value").getAsString();
                BigInteger y = decodeValue(encodedY, base);

                points.add(Map.entry(x, y));
            }

            // Sort points (optional, but may help in debugging or reading data)
            points.sort(Comparator.comparingInt(Map.Entry::getKey));

            // Calculate the constant term using Lagrange interpolation
            BigInteger secret = lagrangeInterpolation(points, k);

            // Output the result
            System.out.println("The secret constant term (c) is: " + secret);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }
}
