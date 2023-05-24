package com.example.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transaksi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.println("======================================");
        System.out.println("=====                            =====");
        System.out.println("=====       Selamat Datang       =====");
        System.out.println("=====                            =====");
        System.out.println("======================================");
        
        System.out.print("Masukkan jumlah produk yang beli: ");
        int jumlahProduk = scanner.nextInt();

        for (int i = 0; i < jumlahProduk; i++) {
            System.out.println("\n===== Data Produk " + (i + 1) + " =====");

            System.out.print("Nama Produk: ");
            String namaProduk = scanner.next();

            System.out.print("Harga: ");
            double harga = scanner.nextDouble();

            System.out.print("Jumlah: ");
            int jumlah = scanner.nextInt();

            System.out.print("Apakah produk ini termasuk kategori makanan? (Y/N): ");
            String isFood = scanner.next();

            Product product;

            if (isFood.equalsIgnoreCase("Y")) {
                System.out.print("Berat (gram): ");
                double berat = scanner.nextDouble();
                product = new FoodProduct(namaProduk, harga, jumlah, berat);
            } else {
                product = new Product(namaProduk, harga, jumlah);
            }

            products.add(product);
        }

        System.out.println("\n===== Transaksi Pembayaran =====");
        double totalHarga = 0;

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println("Produk " + (i + 1) + ": " + product.getNamaProduk());
            System.out.println("Harga: " + product.getHarga());
            System.out.println("Jumlah: " + product.getJumlah());
            System.out.println("Subtotal: " + product.hitungSubtotal());
            System.out.println();

            totalHarga += product.hitungSubtotal();
        }

        System.out.println("Total Harga: " + totalHarga);

        System.out.print("\nMasukkan jumlah uang yang dibayarkan: ");
        double jumlahBayar = scanner.nextDouble();

        double kembalian = jumlahBayar - totalHarga;
        System.out.println("Kembalian: " + kembalian);
        System.out.println("======================================");
        System.out.println("Terima Kasih... ");
    }
}

class Product {
    private String namaProduk;
    private double harga;
    private int jumlah;

    public Product(String namaProduk, double harga, int jumlah) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public double getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double hitungSubtotal() {
        return harga * jumlah;
    }
}

class FoodProduct extends Product {
    private double berat;

    public FoodProduct(String namaProduk, double harga, int jumlah, double berat) {
        super(namaProduk, harga, jumlah);
        this.berat = berat;
    }

    public double getBerat() {
        return berat;
    }

    @Override
    public double hitungSubtotal() {
        return super.hitungSubtotal() * (1 - (berat / 1000)); // Diskon 1% per 1 kg
    }
}
