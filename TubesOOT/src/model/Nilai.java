/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Fatih
 */
public class Nilai {

    private int id_siswa, id_kompomen;
    private float nilai;

    public Nilai(int id_siswa, int id_kompomen, float nilai) {
        this.id_siswa = id_siswa;
        this.id_kompomen = id_kompomen;
        this.nilai = nilai;
    }

    public Nilai(int id_kompomen, float nilai) {
        this.id_kompomen = id_kompomen;
        this.nilai = nilai;
    }
    

    public int getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(int id_siswa) {
        this.id_siswa = id_siswa;
    }

    public int getId_kompomen() {
        return id_kompomen;
    }

    public void setId_kompomen(int id_kompomen) {
        this.id_kompomen = id_kompomen;
    }

    public float getNilai() {
        return nilai;
    }

    public void setNilai(float nilai) {
        this.nilai = nilai;
    }
}
