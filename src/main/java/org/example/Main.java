package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws SQLException, ExceptieVarsta, ExceptieAnExcursie {
        // Din curs
        String url = "jdbc:mysql://localhost:3306/lab8";
        Connection connection = DriverManager.getConnection(url, "root", "elefantul1122.");
        Scanner scanner = new Scanner(System.in);
        int opt;
        do
        {
            System.out.println("\n------------------Meniu----------------------");
            System.out.println("0.Iesire");
            System.out.println("1.Afisare tabela persoane");
            System.out.println("2.Afisare tabela excursii");
            System.out.println("3.Adaugarea unei persoane");
            System.out.println("4.Adaugarea unei excursii");
            System.out.println("5.Afisarea persoanelor si excursiilor");
            System.out.println("6.Afisarea excursiilor unei persoane");
            System.out.println("7.Afisarea persoanelor care au vizitat o anumita destinatie");
            System.out.println("8.Afisarea persoanelor care au vizitat ceva intr-un anumit an");
            System.out.println("9.Stergerea unei excursii");
            System.out.println("10.Stergerea unei persoane");
            System.out.println("Dati optiunea: ");
            opt= scanner.nextInt();
            switch (opt)
            {
                case 0:
                    System.out.println("BYEEEE!");
                    break;
                case 1:
                    afisare_persoane(connection);
                    break;
                case 2:
                    afisare_excursii(connection);
                    break;
                case 3:
                    adaugare_persoane(connection);
                    break;
                case 4:
                    adaugare_excursie(connection);
                    break;
                case 5:
                    afisare_persoana_excursii(connection);
                    break;
                case 6:
                    afisare_excursie_persoana(connection);
                    break;
                case 7:
                    afisarea_persoanelor_vizistat_destinatie(connection);
                    break;
                case 8:
                    afisare_persoane_ani(connection);
                    break;
                case 9:
                    stergere_excursie(connection);
                    break;
                case 10:
                    stergere_persoana(connection);
                    break;
                default:
                    System.out.println("Optiune invalida!");
                    break;
            }
        }while(opt != 0);

        connection.close();
    }

    public static void afisare_persoane(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs =  statement.executeQuery("select * from persoane");
        while (rs.next())
            System.out.println("Id-ul: " + rs.getInt("id") + "\nNume= " + rs.getString("nume") + "\nVarsta=" + rs.getInt("varsta")+"\n");
        statement.close();
        rs.close();
    }

    public static void afisare_excursii(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs= statement.executeQuery("select * from excursii");
        while(rs.next())
            System.out.println("Id ul excursii: "+ rs.getInt("id_excursie")+"\nDestinatie: "+rs.getString("destinatie")+"\nAnul: "+rs.getInt("anul")+"\nId_ul persoanei: "+rs.getInt("id_persoana")+"\n");
        statement.close();
        rs.close();
    }

    public static void adaugare_persoane(Connection connection) throws SQLException, ExceptieVarsta {
        int varsta,id;
        String nume;
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Dati id-ul: ");
        //id = scanner.nextInt(); //11
        System.out.println("Dati numele: ");
        nume= scanner.next(); //Dan
        System.out.println("Dati varsta: ");
        varsta=citeste_varsta(); //38
        String sql="insert into persoane values (?,?,?)";
        String query = "SELECT id FROM persoane ORDER BY id DESC LIMIT 1";
        try (PreparedStatement ps2 = connection.prepareStatement(query)) {
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id") + 1;
            } else {
                id = 1;
            }
        }
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nume);
            ps.setInt(3, varsta);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de adaugare="+nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static int citeste_varsta() throws ExceptieVarsta
    {
        int varsta;
        Scanner scanner = new Scanner(System.in);
        try {
            varsta = scanner.nextInt();
            if (varsta < 0 || varsta > 100)
            {
                throw new ExceptieVarsta("Varsta invalida!");
            }
            return varsta;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new ExceptieVarsta("Ati introdus litere!");
        }
    }

    public static void adaugare_excursie(Connection connection) throws SQLException, ExceptieAnExcursie {
        Scanner scanner = new Scanner(System.in);
        String nume, destinatie;
        int id_pers, id_excursie, varsta, an;
        System.out.println("Verificam DATELE:");
        System.out.println("Dati numele: ");
        nume= scanner.next(); //Dan
        System.out.println("Dati varsta: ");
        varsta=scanner.nextInt(); //38
        if(!verificaExistentaPersoana(connection,nume,varsta)) {
            System.out.println("Aceasta persoana nu exista!");
            return;
        }
        id_pers = obtine_id_persoana(connection,nume,varsta);
        System.out.println("Dati destinatia: ");
        destinatie= scanner.next();
        System.out.println("Dati anul: ");
        an= citeste_anul(connection, id_pers);
        String query = "SELECT id_excursie FROM excursii ORDER BY id_excursie DESC LIMIT 1";
        try (PreparedStatement ps2 = connection.prepareStatement(query)) {
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                id_excursie = rs.getInt("id_excursie") + 1;
            } else {
                id_excursie = 1;
            }
        }
        String sql="insert into excursii values (?,?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id_excursie);
            ps.setString(2, destinatie);
            ps.setInt(3, an);
            ps.setInt(4, id_pers);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de adaugare="+nr_randuri);
        } catch (SQLException e)
        {
            System.out.println(sql);
            e.printStackTrace();
        }
    }


    public static int citeste_anul(Connection connection,int id) throws ExceptieAnExcursie, SQLException {
        int an_nastere,an;
        int varsta=1000;
        Scanner scanner = new Scanner(System.in);
        LocalDate dataCurenta = LocalDate.now();
        int an_c = dataCurenta.getYear();

        String sql="SELECT varsta FROM persoane WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            varsta = resultSet.getInt("varsta");
        }
        else
            throw new ExceptieAnExcursie("Nu s-a gasit persoana cu acest id!");
        an_nastere=an_c - varsta;
        an_c = 2050;
        try {
            an = scanner.nextInt();
            if( an < an_nastere || an > an_c) {
                throw new ExceptieAnExcursie("An invalid! Valoarea prea mare/ mica");
            }
            return an;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new ExceptieAnExcursie("Ati introdus litere!");
        }
    }

    private static boolean verificaExistentaPersoana(Connection connection, String nume,int varsta) throws SQLException {
        String sql = "SELECT nume FROM persoane WHERE nume = ? AND varsta = ?";
        boolean ok;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setInt(2, varsta);
            ResultSet resultSet = ps.executeQuery();
            ok = resultSet.next();
            if(ok) {
                System.out.println("Date OK, persoana existenta");
            }
            return ok;
        }
    }

    public static int obtine_id_persoana(Connection connection, String nume, int varsta) throws SQLException
    {
        String sql = "SELECT id FROM persoane WHERE nume = ? AND varsta = ?";
        int id=0;
        try(PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1,nume);
            ps.setInt(2,varsta);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                id = rs.getInt("id");
        }
        return id;
    }

    public static void afisare_persoana_excursii(Connection connection) throws SQLException
    {
        String sql = "SELECT " +
                "p.id AS id_persoana, " +
                "p.nume AS nume_persoana, " +
                "p.varsta AS varsta_persoana, " +
                "e.id_excursie, " +
                "e.destinatie, " +
                "e.anul " +
                "FROM persoane p " +
                "LEFT JOIN excursii e ON p.id = e.id_persoana";
        try (PreparedStatement ps= connection.prepareStatement(sql))
        {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int idPersoana = resultSet.getInt("id_persoana");
                String numePersoana = resultSet.getString("nume_persoana");
                int varstaPersoana = resultSet.getInt("varsta_persoana");
                int idExcursie = resultSet.getInt("id_excursie");
                String destinatie = resultSet.getString("destinatie");
                int anul = resultSet.getInt("anul");
                System.out.println("ID Persoana: " + idPersoana +
                        ", Nume Persoana: " + numePersoana +
                        ", Varsta Persoana: " + varstaPersoana +
                        ", ID Excursie: " + idExcursie +
                        ", Destinatia: " + destinatie +
                        ", Anul: " + anul);
            }
        }
    }

    public static void afisare_excursie_persoana(Connection connection) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dati numele unei persoane: ");
        String nume= scanner.next();
        String sql = "SELECT " +
                "p.id AS id_persoana, " +
                "p.nume AS nume_persoana, " +
                "p.varsta AS varsta_persoana, " +
                "e.id_excursie, " +
                "e.destinatie, " +
                "e.anul " +
                "FROM persoane p " +
                "LEFT JOIN excursii e ON p.id = e.id_persoana " +
                "WHERE p.nume = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nume);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int idPersoana = resultSet.getInt("id_persoana");
                String numePersoanaDB = resultSet.getString("nume_persoana");
                int varstaPersoana = resultSet.getInt("varsta_persoana");
                int idExcursie = resultSet.getInt("id_excursie");
                String destinatie = resultSet.getString("destinatie");
                int anul = resultSet.getInt("anul");

                System.out.println("ID Persoana: " + idPersoana +
                        ", Nume Persoana: " + numePersoanaDB +
                        ", Varsta Persoana: " + varstaPersoana +
                        ", ID Excursie: " + idExcursie +
                        ", Destinatia: " + destinatie +
                        ", Anul: " + anul);
            }
        }
    }

    public static void afisarea_persoanelor_vizistat_destinatie(Connection connection) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dati destinatia: ");
        String destinatie= scanner.next();
        String sql = "SELECT " +
                "p.id AS id_persoana, " +
                "p.nume AS nume_persoana, " +
                "p.varsta AS varsta_persoana, " +
                "e.id_excursie, " +
                "e.destinatie, " +
                "e.anul " +
                "FROM persoane p " +
                "JOIN excursii e ON p.id = e.id_persoana " +
                "WHERE e.destinatie = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, destinatie);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idPersoana = resultSet.getInt("id_persoana");
                    String numePersoana = resultSet.getString("nume_persoana");
                    int varstaPersoana = resultSet.getInt("varsta_persoana");
                    int idExcursie = resultSet.getInt("id_excursie");
                    int anul = resultSet.getInt("anul");

                    System.out.println("ID Persoana: " + idPersoana +
                            ", Nume Persoana: " + numePersoana +
                            ", Varsta Persoana: " + varstaPersoana +
                            ", ID Excursie: " + idExcursie +
                            ", Destinatia: " + destinatie +
                            ", Anul: " + anul);
                }
            }
        }
    }

    public static void afisare_persoane_ani(Connection connection) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dati anul: ");
        int anul= scanner.nextInt();
        String sql = "SELECT " +
                "p.id AS id_persoana, " +
                "p.nume AS nume_persoana, " +
                "p.varsta AS varsta_persoana, " +
                "e.id_excursie, " +
                "e.destinatie, " +
                "e.anul " +
                "FROM persoane p " +
                "JOIN excursii e ON p.id = e.id_persoana " +
                "WHERE e.anul = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, anul);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    int idPersoana = resultSet.getInt("id_persoana");
                    String numePersoana = resultSet.getString("nume_persoana");
                    int varstaPersoana = resultSet.getInt("varsta_persoana");
                    int idExcursie = resultSet.getInt("id_excursie");
                    String destinatie = resultSet.getString("destinatie");

                    System.out.println("ID Persoana: " + idPersoana +
                            ", Nume Persoana: " + numePersoana +
                            ", Varsta Persoana: " + varstaPersoana +
                            ", ID Excursie: " + idExcursie +
                            ", Destinatia: " + destinatie +
                            ", Anul: " + anul);
                }
            }
        }
    }

    public static void stergere_excursie(Connection connection) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dati id-ul excursiei: ");
        int id_e = scanner.nextInt();
        String sql = "DELETE FROM excursii WHERE id_excursie = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,id_e);
            int nrRanduriAfectate = preparedStatement.executeUpdate();
            if(nrRanduriAfectate == 0)
                System.out.println("Nu exista nicio excursie cu acest id!");
            else
                System.out.println("Stergere efectuata cu success!");
        }
    }

    public static void stergere_persoana(Connection connection) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dati id-ul persoanei: ");
        int id_pers= scanner.nextInt();
        stergere_excursie_dupa_id_persoana(connection,id_pers);
        String sql = "DELETE FROM persoane WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,id_pers);
            int nrRanduriAfectate = preparedStatement.executeUpdate();
            if(nrRanduriAfectate == 1)
                System.out.println("Persoana a fost stearsa");
            else
                System.out.println("Nu a fost sters nimeni");
        }
    }

    public static void stergere_excursie_dupa_id_persoana(Connection connection, int id_pers) throws SQLException
    {
        String sql = "DELETE FROM excursii WHERE id_persoana = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,id_pers);
            int nrRanduriAfectate = preparedStatement.executeUpdate();
            if(nrRanduriAfectate > 0)
                System.out.println("Au fost sterse "+ nrRanduriAfectate+" excursii");
            else
                System.out.println("Nu au fost sterse nici o excursie!");
        }
    }

}
