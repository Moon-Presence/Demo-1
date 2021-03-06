package sample;

import javafx.scene.image.Image;

import java.util.Random;

public class ProcessController {

    //units load
    public  final Image laser = new Image("sample//Resources//lasertank.png");
    public  final Image ghoust = new Image("sample//Resources//ghoust.png");
    public  final Image tank = new Image("sample//Resources//tank.png");
    public  final Image jet = new Image("sample//Resources//sand_jet.png");
    public  final Image reaper = new Image("sample//Resources//reaper.png");
    public  final Image art = new Image("sample//Resources//art.png");
    public  final Image elaser = new Image("sample//Resources//elasertank.png");
    public  final Image etank = new Image("sample//Resources//etank.png");
    public  final Image ejet = new Image("sample//Resources//esand_jet.png");
    public  final Image ereaper = new Image("sample//Resources//ereaper.png");
    public  final Image eart = new Image("sample//Resources//eart.png");
    public  final Image space = new Image("sample//Resources//0.png");
    //background load
    public  final Image ruine_s = new Image("sample//Resources//5s.png");
    public  final Image water_s = new Image("sample//Resources//3s.png");
    public  final Image tree_s = new Image("sample//Resources//4s.png");
    public  final Image ground_s = new Image("sample//Resources//2s.png");
    public  final Image point_s = new Image("sample//Resources//fs.png");
    public  final Image ruine = new Image("sample//Resources//5.png");
    public  final Image water = new Image("sample//Resources//3.png");
    public  final Image tree = new Image("sample//Resources//4.png");
    public  final Image ground = new Image("sample//Resources//2.png");
    public  final Image point = new Image("sample//Resources//f.png");

    public static String context="1deploy";
    public static int N=5;// units quantity
    public static int coordinate_i, coordinate_j;
    public static int cnt=0;//common number tracker
    public static int pp = 0, bp=0;//player points, bot points
    public static int A = 0, B = 0;// Camera coordinates
    public static int round=1;

    public static char [][] a = new char[11][19];//графический буфер
    public static char [][] b = new char[11][19];//объектный буфер

    public static Model unit[] = new Model[20];//игровые юниты

    //готовая часть

    public void restoring(int u, String s)
    {
        switch (s)
        {
            case "Tank":   if(u<10)unit[u].id = (char)(48 + u); else unit[u].id = (char)('A' +(char)(u-10)); unit[u].HP = 6; unit[u].AP = 2; unit[u].w1ac = 25; unit[u].w2ac = 0;  unit[u].spec_ac = 0;  unit[u].damage1 = 2; unit[u].damage2 = 0; unit[u].Effect = ""; unit[u].Special = "Barricade";          unit[u].W1 = "Canon";           unit[u].W2 = ""; break;
            case "Jet":    if(u<10)unit[u].id = (char)(48 + u); else unit[u].id = (char)('A' +(char)(u-10)); unit[u].HP = 2; unit[u].AP = 2; unit[u].w1ac = 20; unit[u].w2ac = 0;  unit[u].spec_ac = 30; unit[u].damage1 = 2; unit[u].damage2 = 0; unit[u].Effect = ""; unit[u].Special = "Сarpet bombardment"; unit[u].W1 = "Rockets";         unit[u].W2 = ""; break;
            case "Reaper": if(u<10)unit[u].id = (char)(48 + u); else unit[u].id = (char)('A' +(char)(u-10)); unit[u].HP = 4; unit[u].AP = 2; unit[u].w1ac = 25; unit[u].w2ac = 20; unit[u].spec_ac = 25; unit[u].damage1 = 3; unit[u].damage2 = 2; unit[u].Effect = ""; unit[u].Special = "Reaper's fury";      unit[u].W1 = "Gatling laser";   unit[u].W2 = "Rockets"; break;
            case "Ghoust": if(u<10)unit[u].id = (char)(48 + u); else unit[u].id = (char)('A' +(char)(u-10)); unit[u].HP = 4; unit[u].AP = 2; unit[u].w1ac = 15; unit[u].w2ac = 25; unit[u].spec_ac = 0;  unit[u].damage1 = 3; unit[u].damage2 = 2; unit[u].Effect = ""; unit[u].Special = "Invisible";          unit[u].W1 = "Invisible sword"; unit[u].W2 = "Thunder gun"; break;
            case "Laser":  if(u<10)unit[u].id = (char)(48 + u); else unit[u].id = (char)('A' +(char)(u-10)); unit[u].HP = 4; unit[u].AP = 2; unit[u].w1ac = 0;  unit[u].w2ac = 0;  unit[u].spec_ac = 0;  unit[u].damage1 = 4; unit[u].damage2 = 0; unit[u].Effect = ""; unit[u].Special = "";                   unit[u].W1 = "Laser canon";     unit[u].W2 = ""; break;
            case "Art":    if(u<10)unit[u].id = (char)(48 + u); else unit[u].id = (char)('A' +(char)(u-10)); unit[u].HP = 5; unit[u].AP = 1; unit[u].w1ac = 35; unit[u].w2ac = 0;  unit[u].spec_ac = 0;  unit[u].damage1 = 4; unit[u].damage2 = 0; unit[u].Effect = ""; unit[u].Special = "";                   unit[u].W1 = "Artillery canon"; unit[u].W2 = ""; break;
        } if(u<10) Controller.TextBox1.setText( Controller.TextBox1.getText()+unit[u].id +unit[u].Type+"\r\n") ;
    }

    //Graphics
    //{
    public void d_rendering()
    {
        for(int i =0;i<7;i++)
            for(int j =0;j<2;j++){
                switch (a[A + i/9][ B + i%9]) {
                    case 'r': Controller.bg[i].setImage(ruine); break;
                    case 'w': Controller.bg[i].setImage(water); break;
                    case 't': Controller.bg[i].setImage(tree); break;
                    case 'g': Controller.bg[i].setImage(ground); break;
                    case 'p': Controller.bg[i].setImage(point);break;
                    case 'R': Controller.bg[i].setImage(ruine_s); break;
                    case 'W': Controller.bg[i].setImage(water_s); break;
                    case 'T': Controller.bg[i].setImage(tree_s); break;
                    case 'G': Controller.bg[i].setImage(ground_s); break;
                    case 'P': Controller.bg[i].setImage(point_s); break; }

                if(    (b[A + i/9][ B + i%9]>='0'  &
                         b[A + i/9][ B + i%9]<='9') ||
                        (b[A + i/9][ B + i%9]>='0'  &
                         b[A + i/9][ B + i%9]<='9')){
                    switch (unit[b[A + i/9][ B + i%9]].Type) {
                        case "Tank":   Controller.fg[i].setImage(tank); break;
                        case "Jet":    Controller.fg[i].setImage(jet); break;
                        case "Reaper": Controller.fg[i].setImage(reaper); break;
                        case "Art":    Controller.fg[i].setImage(art); break;
                        case "Laser":  Controller.fg[i].setImage(laser); break;
                        case "Ghoust": Controller.fg[i].setImage(ghoust);break; }}
                else{
                    Controller.fg[i].setImage(space);}}


    }
    public void rendering1()
    {
        for(int i=0;i<63;i++){

        //background

            switch (a[A + i/9][ B + i%9]) {
                case 'r': Controller.bg[i].setImage(ruine); break;
                case 'w': Controller.bg[i].setImage(water); break;
                case 't': Controller.bg[i].setImage(tree); break;
                case 'g': Controller.bg[i].setImage(ground); break;
                case 'p': Controller.bg[i].setImage(point);break;
                case 'R': Controller.bg[i].setImage(ruine_s); break;
                case 'W': Controller.bg[i].setImage(water_s); break;
                case 'T': Controller.bg[i].setImage(tree_s); break;
                case 'G': Controller.bg[i].setImage(ground_s); break;
                case 'P': Controller.bg[i].setImage(point_s); break; }

        // object

        if(   (b[A + i/9][ B + i%9]>='0'  &
               b[A + i/9][ B + i%9]<='9') ||
              (b[A + i/9][ B + i%9]>='0'  &
               b[A + i/9][ B + i%9]<='9')
          ){
            switch (unit[b[A + i/9][ B + i%9]].Type) {
                case "Tank":   Controller.fg[i].setImage(tank); break;
                case "Jet":    Controller.fg[i].setImage(jet); break;
                case "Reaper": Controller.fg[i].setImage(reaper); break;
                case "Art":    Controller.fg[i].setImage(art); break;
                case "Laser":  Controller.fg[i].setImage(laser); break;
                case "Ghoust": Controller.fg[i].setImage(ghoust);break; }}
        else{
            Controller.fg[i].setImage(space);}}
    }
    //}


    //Bot
    //{
    public void b_deploy(int u)
    {
        for (int i = 0; i < 11; i++)
            for (int j = 17; j < 19; j++)
                if ((a[i][ j] != 'w' || (a[i][ j] == 'w' && unit[u].Type == "Jet"))&&b[i][j]==' ') {
                    unit[u].I = i;
                    unit[u].J = j;
                    b[i][ j] = unit[u].id;
                    return; }
    }
    public void b_move(int u,int k,int l)
    {int jd=100, id=100;
        switch (unit[u].Type)
        {

            case "Tank":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 2)
                            if (Math.abs(k - i) <= Math.abs(k - id) && Math.abs(l - j) <= Math.abs(l - jd))
                                if (b[i][ j] == ' ' && a[i][ j] != 'w' ) {
                                    id = i;
                                    jd = j; }
            b[id][ jd] = unit[u].id;
            b[unit[u].I][ unit[u].J] = ' ';
            unit[u].I = id;
            unit[u].J = jd;
            break;


            case "Art":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 2)
                            if (Math.abs(k - i) < Math.abs(k - id) && Math.abs(l - j) < Math.abs(l - jd))
                                if (b[i][ j] == ' ' && a[i][ j] != 'w'){
                                    id = i;
                                    jd = j; }
            b[id][ jd] = unit[u].id;
            b[unit[u].I][ unit[u].J] = ' ';
            unit[u].I = id;
            unit[u].J = jd;
            break;

            case "Reaper":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 2)
                            if (Math.abs(k - i) < Math.abs(k - id) && Math.abs(l - j) < Math.abs(l - jd))
                                if (b[i][j] == ' ' && a[i][j] != 'w')
            { id = i; jd = j; }
            b[id][jd] = unit[u].id;
            b[unit[u].I][unit[u].J] = ' ';
            unit[u].I = id;
            unit[u].J = jd;
            break;


            case "Ghoust":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 2)
                            if (Math.abs(k - i) < Math.abs(k - id) && Math.abs(l - j) < Math.abs(l - jd))
                                if (b[i][j] == ' ' && a[i][j] != 'w')
            { id = i; jd = j; }
            b[id][jd] = unit[u].id;
            b[unit[u].I][unit[u].J] = ' ';
            unit[u].I = id;
            unit[u].J = jd;
            break;


            case "Laser":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 2)
                            if (Math.abs(k - i) < Math.abs(k - id) && Math.abs(l - j) < Math.abs(l - jd))
                                if (b[i][j] == ' ' && a[i][j] != 'w')
            { id = i; jd = j; }
            b[id][jd] = unit[u].id;
            b[unit[u].I][unit[u].J] = ' ';
            unit[u].I = id;
            unit[u].J = jd;
            break;


            case "Jet":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 4)
                            if (Math.abs(k - i) < Math.abs(k - id) && Math.abs(l - j) < Math.abs(l - jd))
                                if (b[i][j] == ' ')
            { id = i; jd = j; }
            b[id][jd] = unit[u].id;
            b[unit[u].I][unit[u].J] = ' ';
            unit[u].I = id;
            unit[u].J = jd;
            break;
        }
        rendering1();
    }
    public boolean b_attack1(int u)
    {
        Random r = new Random();
        int abs1,abs2;
        switch (unit[u].Type)
        {
            case "Tank":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 2)
                            if (b[i][j] >= '0' && b[i][j] <= '9')
                if (r.nextInt(100) > unit[u].w1ac)
                {
                    switch (b[i][j])
                    {
                        case '0': cnt = 0; break;
                        case '1': cnt = 1; break;
                        case '2': cnt = 2; break;
                        case '3': cnt = 3; break;
                        case '4': cnt = 4; break;
                        case '5': cnt = 5; break;
                        case '6': cnt = 6; break;
                        case '7': cnt = 7; break;
                        case '8': cnt = 8; break;
                        case '9': cnt = 9; break;
                    }

                    switch (unit[cnt].Type)
                    {
                        case "Tank": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Jet": Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true;
                        case "Reaper": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Art": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Ghoust": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Laser": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                    }
                    if (unit[cnt].HP < 1) b[unit[cnt].I][unit[cnt].J] = ' ';
                    Controller.TextBox1.setText( "Our unit take " + unit[u].damage1 + " damage in " + (i + 1) + " " + (j + 1) + "\r\n"); return true;
                }
                else
                { Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true; }
                break;


            case "Art":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                    {
                        abs1 = Math.abs(unit[u].I - i); abs2 = Math.abs(unit[u].J - j);
                        if ((abs1 > 1 || abs2 > 1) && abs1 <= 5 && abs2 <= 5 && abs1 + abs2 <= 8)
                            if (b[i][j] >= '0' && b[i][j] <= '9')
                        if (r.nextInt(100) > unit[u].w1ac)
                        {
                            switch (b[i][j])
                            {
                                case '0': cnt = 0; break;
                                case '1': cnt = 1; break;
                                case '2': cnt = 2; break;
                                case '3': cnt = 3; break;
                                case '4': cnt = 4; break;
                                case '5': cnt = 5; break;
                                case '6': cnt = 6; break;
                                case '7': cnt = 7; break;
                                case '8': cnt = 8; break;
                                case '9': cnt = 9; break;
                            }

                            switch (unit[cnt].Type)
                            {
                                case "Tank": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                case "Jet": Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true;
                                case "Reaper": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                case "Art": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                case "Ghoust": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                case "Laser": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                            }
                            if (unit[cnt].HP < 1) b[unit[cnt].I][unit[cnt].J] = ' ';
                            Controller.TextBox1.setText( "Our unit take " + unit[u].damage1 + " damage in " + (i + 1) + " " + (j + 1) + "\r\n"); return true;
                        }
                        else
                        {
                            Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true;
                        }
                    }
                break;

            case "Reaper":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 2)
                            if (r.nextInt(100) > unit[u].w1ac)
                            {
                                switch (b[i][j])
                                {
                                    case '0': cnt = 0; break;
                                    case '1': cnt = 1; break;
                                    case '2': cnt = 2; break;
                                    case '3': cnt = 3; break;
                                    case '4': cnt = 4; break;
                                    case '5': cnt = 5; break;
                                    case '6': cnt = 6; break;
                                    case '7': cnt = 7; break;
                                    case '8': cnt = 8; break;
                                    case '9': cnt = 9; break;
                                }

                                switch (unit[cnt].Type)
                                {
                                    case "Tank": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                    case "Jet":unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                    case "Reaper": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                    case "Art": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                    case "Ghoust": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                    case "Laser": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                                }
                                if (unit[cnt].HP < 1) b[unit[cnt].I][unit[cnt].J] = ' ';
                                Controller.TextBox1.setText( "Our unit take " + unit[u].damage1 + " damage in " + (i + 1) + " " + (j + 1) + "\r\n"); return true;
                            }
                            else
                            { Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true; }
                break;


            case "Ghoust":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 2)
                            if (b[i][j] >= '0' && b[i][j] <= '9')
                if (r.nextInt(100) > unit[u].w1ac)
                {
                    switch (b[i][j])
                    {
                        case '0': cnt = 0; break;
                        case '1': cnt = 1; break;
                        case '2': cnt = 2; break;
                        case '3': cnt = 3; break;
                        case '4': cnt = 4; break;
                        case '5': cnt = 5; break;
                        case '6': cnt = 6; break;
                        case '7': cnt = 7; break;
                        case '8': cnt = 8; break;
                        case '9': cnt = 9; break;
                    }

                    switch (unit[cnt].Type)
                    {
                        case "Tank": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Jet": Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true;
                        case "Reaper": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Art": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Ghoust": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Laser": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                    }
                    if (unit[cnt].HP < 1) b[unit[cnt].I][unit[cnt].J] = ' ';
                    Controller.TextBox1.setText( "Our unit take " + unit[u].damage1 + " damage in " + (i + 1) + " " + (j + 1) + "\r\n"); return true;
                }
                else
                { Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true; }
                break;

            case "Laser":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if (((unit[u].I - unit[u].J) == (i - j)) || ((unit[u].I + unit[u].J) == (i + j)) || i == unit[u].I || j == unit[u].J)
                            if (b[i][j] >= '0' && b[i][j] <= '9')
                if (r.nextInt(100) > unit[u].w1ac)
                {
                    switch (b[i][j])
                    {
                        case '0': cnt = 0; break;
                        case '1': cnt = 1; break;
                        case '2': cnt = 2; break;
                        case '3': cnt = 3; break;
                        case '4': cnt = 4; break;
                        case '5': cnt = 5; break;
                        case '6': cnt = 6; break;
                        case '7': cnt = 7; break;
                        case '8': cnt = 8; break;
                        case '9': cnt = 9; break;
                    }

                    switch (unit[cnt].Type)
                    {
                        case "Tank": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Jet": unit[cnt].HP = unit[cnt].HP - unit[u].damage1;break;
                        case "Reaper": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Art": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Ghoust": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Laser": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                    }
                    if (unit[cnt].HP < 1) b[unit[cnt].I][unit[cnt].J] = ' ';
                    Controller.TextBox1.setText( "Our unit take " + unit[u].damage1 + " damage in " + (i + 1) + " " + (j + 1) + "\r\n"); return true;
                }
                else
                { Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true; }
                break;


            case "Jet":
                for (int i = 0; i < 11; i++)
                    for (int j = 0; j < 19; j++)
                        if ((Math.abs(unit[u].I - i) + Math.abs(unit[u].J - j)) <= 4)
                            if (b[i][j] >= '0' && b[i][j] <= '9')
                if (r.nextInt(100) > unit[u].w1ac)
                {
                    switch (b[i][j])
                    {
                        case '0': cnt = 0; break;
                        case '1': cnt = 1; break;
                        case '2': cnt = 2; break;
                        case '3': cnt = 3; break;
                        case '4': cnt = 4; break;
                        case '5': cnt = 5; break;
                        case '6': cnt = 6; break;
                        case '7': cnt = 7; break;
                        case '8': cnt = 8; break;
                        case '9': cnt = 9; break;
                    }

                    switch (unit[cnt].Type)
                    {
                        case "Tank": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Jet":unit[cnt].HP = unit[cnt].HP - unit[u].damage1;break;
                        case "Reaper": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Art": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Ghoust": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                        case "Laser": unit[cnt].HP = unit[cnt].HP - unit[u].damage1; break;
                    }
                    if (unit[cnt].HP < 1) b[unit[cnt].I][unit[cnt].J] = ' ';
                    Controller.TextBox1.setText( "Our unit take " + unit[u].damage1 + " damage in " + (i + 1) + " " + (j + 1) + "\r\n"); return true;
                }
                else
                { Controller.TextBox1.setText( "Our unit is under fire in " + (j + 1) + " " + (i + 1) + "\r\n"); return true; }
                break;

        } return false;
    }
    //}

    public void reaction()
    {
        Controller.TextBox1.setText( "" + coordinate_i + " " + coordinate_j);
        switch (context)
        {
            case "deploy": deploy(); break;
            case "select": selecting();  break;
            case "move": move(); break;
            case "w1": attack1(); break;
            case "w2": attack2(); break;
            case "special": special(); break;
        }
    }



    //{
    public void deploy() //переделать
    {/*
        if (b[coordinate_i][coordinate_j] != ' ') return;
        unit[cnt].I = coordinate_i; unit[cnt].J = coordinate_j; b[coordinate_i][coordinate_j] = unit[cnt].id; selecting();

        cnt++;
        if (cnt == N) { cnt--; context = "select";textBox6.setText( "1 Turn");
            Controller.TextBox1.setText( "Unit's has been successfull deployed"); RIGHT.Enabled = true;Menu.Enabled = true; rendering1(); b3.Enabled = true;
            b4.Enabled = true;
            b5.Enabled = true;
            b6.Enabled = true;
            b7.Enabled = true;
            b8.Enabled = true;
            b9.Enabled = true;
            b12.Enabled = true;
            b13.Enabled = true;
            b14.Enabled = true;
            b15.Enabled = true;
            b16.Enabled = true;
            b17.Enabled = true;
            b18.Enabled = true;
            b21.Enabled = true;
            b22.Enabled = true;
            b23.Enabled = true;
            b24.Enabled = true;
            b25.Enabled = true;
            b26.Enabled = true;
            b27.Enabled = true;
            b30.Enabled = true;
            b31.Enabled = true;
            b32.Enabled = true;
            b33.Enabled = true;
            b34.Enabled = true;
            b35.Enabled = true;
            b36.Enabled = true;
            b39.Enabled = true;
            b40.Enabled = true;
            b41.Enabled = true;
            b42.Enabled = true;
            b43.Enabled = true;
            b44.Enabled = true;
            b45.Enabled = true;
            b48.Enabled = true;
            b49.Enabled = true;
            b50.Enabled = true;
            b51.Enabled = true;
            b52.Enabled = true;
            b53.Enabled = true;
            b54.Enabled = true;
            b57.Enabled = true;
            b58.Enabled = true;
            b59.Enabled = true;
            b60.Enabled = true;
            b61.Enabled = true;
            b62.Enabled = true;
            b63.Enabled = true; return;  }
        d_rendering();*/
    }
    public void selecting() //переделать
    {/*
        boolean e = false;
        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 19; j++)
                switch (a[i][j])
        {
            case 'R': a[i][j] = 'r'; break;
            case 'W': a[i][j] = 'w';  break;
            case 'T': a[i][j] = 't'; break;
            case 'G': a[i][j] = 'g'; break;
            case 'P': a[i][j] = 'p';  break;
        }

        Mb.Enabled = false;
        Stb.Enabled = false;
        W1b.Enabled = false;
        Sb.Enabled = false;
        W2b.Enabled = false; W2b.Visible = true;

        switch (b[coordinate_i][coordinate_j])
        {
            case ' ': pb.Image = space; break;
            case '0': cnt = 0; e = false; break;
            case '1': cnt = 1; e = false;break;
            case '2': cnt = 2; e = false;break;
            case '3': cnt = 3; e = false;break;
            case '4': cnt = 4; e = false;break;
            case '5': cnt = 5; e = false;break;
            case '6': cnt = 6; e = false;break;
            case '7': cnt = 7; e = false;break;
            case '8': cnt = 8; e = false;break;
            case '9': cnt = 9; e = false; break;
            case 'A': cnt = 10; e = true; break;
            case 'B': cnt = 11; e = true;break;
            case 'C': cnt = 12; e = true;break;
            case 'D': cnt = 13; e = true;break;
            case 'E': cnt = 14; e = true;break;
            case 'F': cnt = 15; e = true;break;
            case 'G': cnt = 16; e = true;break;
            case 'H': cnt = 17; e = true;break;
            case 'I': cnt = 18; e = true;break;
            case 'J': cnt = 19; e = true; break;
        }

        switch (a[coordinate_i][coordinate_j])
        {
            case 'r': a[coordinate_i][coordinate_j] = 'R'; textBox2.setText( "Ruin";pb.BackgroundImage = ruine; break;
            case 'w': a[coordinate_i][coordinate_j] = 'W'; textBox2.setText( "Water"; pb.BackgroundImage = water; break;
            case 't': a[coordinate_i][coordinate_j] = 'T'; textBox2.setText( "Wood"; pb.BackgroundImage = tree; break;
            case 'g': a[coordinate_i][coordinate_j] = 'G'; textBox2.setText( "None"; pb.BackgroundImage = ground; break;
            case 'p': a[coordinate_i][coordinate_j] = 'P'; textBox2.setText( "Strategic point"; pb.BackgroundImage = point; break;
        }
        if (context != "deploy") rendering1();

        if (b[coordinate_i][coordinate_j] != ' ')
        {

            if (!e)
            {
                if (unit[cnt].AP > 0) Mb.Enabled = true;
                Stb.Enabled = true;
                if (unit[cnt].AP > 0) W1b.Enabled = true;
                //if (((/*unit[cnt].Type == "Jet" || unit[cnt].Type == "Reaper") && unit[cnt].AP == 2) || ((unit[cnt].Type == "Tank" || unit[cnt].Type == "Ghoust") && unit[cnt].AP >= 1)) Sb.Enabled = true;
                switch (unit[cnt].Type)
                {
                    case "Tank": W2b.Visible = false; pb.Image = tank; break;
                    case "Jet": W2b.Visible = false; pb.Image = jet; break;
                    case "Reaper": if (unit[cnt].AP >= 0) W2b.Enabled = true; pb.Image = reaper; break;
                    case "Ghoust": if (unit[cnt].AP >= 0) W2b.Enabled = true; pb.Image = ghoust; break;
                    case "Art": W2b.Visible = false; pb.Image = art; break;
                    case "Laser": W2b.Visible = false; pb.Image = laser; break;
                }
            }
            else
                switch (unit[cnt].Type)
                {
                    case "Tank": pb.Image = etank; break;
                    case "Jet": pb.Image = ejet; break;
                    case "Reaper": pb.Image = ereaper; break;
                    case "Art": pb.Image = eart; break;
                    case "Laser": pb.Image = elaser; break;
                }

            textBox2.setText( textBox2.Text + "\r\n------------------------------\r\n"); if(unit[cnt].Type!="None")textBox2.setText( textBox2.getText + "Type: " + unit[cnt].Type + "\r\n" + "Hp: " + unit[cnt].HP + "\r\n" + "Ap: " + unit[cnt].AP + "\r\n" + "Weapon 1:("+unit[cnt].damage1+"dmg)\r\n"+ unit[cnt].W1+ "\r\n"+ "Weapon 2:(" + unit[cnt].damage2 + "dmg)\r\n" + unit[cnt].W2 + "\r\n"+ "Special: " + unit[cnt].Special + "\r\n"+ "Effect: " + unit[cnt].Effect + "\r\n";
        }*/

    }
    public void move()
    {
        if (a[coordinate_i][coordinate_j] <'a'&& ((b[coordinate_i][coordinate_j] == ' ' && (a[coordinate_i][coordinate_j]) != 'W' || (a[coordinate_i][coordinate_j] == 'W' && unit[cnt].Type == "Jet"))))
        {
            b[coordinate_i][coordinate_j] = unit[cnt].id;
            b[unit[cnt].I][unit[cnt].J] = ' ';
            unit[cnt].I = coordinate_i;unit[cnt].J = coordinate_j;

            for (int i = 0; i < 11; i++)
                for (int j = 0; j < 19; j++)
                    switch (a[i][j])
            {
                case 'R': a[i][j] = 'r'; break;
                case 'W': a[i][j] = 'w'; break;
                case 'T': a[i][j] = 't'; break;
                case 'G': a[i][j] = 'g'; break;
                case 'P': a[i][j] = 'p'; break;
            }
            rendering1();
            context = "select"; unit[cnt].AP--;selecting();
        }
    }
    public void attack1()
    {
        int d=0;
        Random r = new Random();

        if (a[ coordinate_i][ coordinate_j] < 'a')
        if (b[coordinate_i][coordinate_j] <= 'J' && b[coordinate_i][coordinate_j] >= 'A' && r.nextInt(100) > unit[cnt].w1ac)
        { switch (b[coordinate_i][ coordinate_j])
            {
                case 'A':d = 10;  break;
                case 'B':d = 11;  break;
                case 'C':d = 12;  break;
                case 'D':d = 13;  break;
                case 'E':d = 14;  break;
                case 'F':d = 15;  break;
                case 'G':d = 16;  break;
                case 'H':d = 17;  break;
                case 'I':d = 18;  break;
                case 'J': d = 19; break;

            }

            switch (unit[d].Type)
            {
                case "Tank":
                case "Reaper":
                case "Art":
                case "Ghoust":
                case "Laser": unit[d].HP = unit[d].HP - unit[cnt].damage1;  break;
                case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[d].HP = unit[d].HP - unit[cnt].damage1; ; break;

            }
            if (unit[d].HP < 1) { b[unit[d].I][unit[d].J] = ' ';unit[d].Effect = "KIA"; }



            Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage"); }
                else
        Controller.TextBox1.setText( "Miss");
        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 19; j++)
                switch (a[i][j])
        {
            case 'R': a[i][j] = 'r'; break;
            case 'W': a[i][j] = 'w'; break;
            case 'T': a[i][j] = 't'; break;
            case 'G': a[i][j] = 'g'; break;
            case 'P': a[i][j] = 'p'; break;
        } rendering1(); unit[cnt].AP--;
        context = "select"; coordinate_i = unit[cnt].I;  coordinate_j = unit[cnt].J;   selecting();

    }
    public void attack2()
    {
        Random r = new Random();

        if (a[ coordinate_i][ coordinate_j] < 'a')
        if (b[ coordinate_i][ coordinate_j] != ' ' && r.nextInt(100) > unit[cnt].w2ac)
        {
            switch (b[ coordinate_i][coordinate_j])
            {
                case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage2; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage2; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage2; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage2; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage2; break; } break;
                case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage2; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage2; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage2; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage2; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage2; break; } break;
                case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage2; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage2; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage2; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage2; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage2; break; } break;
                case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage2; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage2; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage2; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage2; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage2; break; } break;
                case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage2; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage2; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage2; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage2; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage2; break; } break;
                case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage2; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage2; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage2; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage2; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage2; break; } break;
                case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage2; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage2; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage2; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage2; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage2; break; } break;
                case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage2; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage2; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage2; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage2; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage2; break; } break;
                case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage2; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage2; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage2; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage2; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage2; break; } break;
                case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage2; break; case "Jet": if (unit[cnt].Type == "Reaper" ||unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage2; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage2; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage2; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage2; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage2; break; } break;

            }
            Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage2 + " damage");
        }
                else
        Controller.TextBox1.setText( "Miss"); for (int i = 0; i < 11; i++)
        for (int j = 0; j < 19; j++)
            switch (a[i][j])
        {
            case 'R': a[i][j] = 'r'; break;
            case 'W': a[i][j] = 'w'; break;
            case 'T': a[i][j] = 't'; break;
            case 'G': a[i][j] = 'g'; break;
            case 'P': a[i][j] = 'p'; break;
        }
        rendering1();
        context = "select"; coordinate_i = unit[cnt].I; coordinate_j = unit[cnt].J; selecting();

    }
    public void special()
    {
        Random r = new Random();
        switch (unit[cnt].Type)
        {
            case "Jet": switch (unit[cnt].J - coordinate_j)
            {
                case 5:
                    for (int i = 0; i < 11; i++)
                        for (int j = 0; j < 19; j++)
                            if ((j - coordinate_j) <= 3 && (j - coordinate_j) >= 1 && Math.abs(coordinate_i - i) <= 1)
                                if (a[i][j] < 'a')
                    if (b[i][j] != ' ' && r.nextInt(100) > unit[cnt].w1ac)
                {
                    switch (b[i][j])
                    {
                        case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage1; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; } break;
                        case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage1; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; } break;
                        case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage1; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; } break;
                        case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage1; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; } break;
                        case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage1; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; } break;
                        case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage1; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; } break;
                        case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage1; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; } break;
                        case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage1; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; } break;
                        case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage1; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; } break;
                        case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage1; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; } break;

                    }
                    Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage");
                }
                                            else
                Controller.TextBox1.setText( "Miss");
                break;


                case -5:
                    for (int i = 0; i < 11; i++)
                        for (int j = 0; j < 19; j++)
                            if ((j - coordinate_j) <= -1 && (j - coordinate_j) >= -3 && Math.abs(coordinate_i - i) <= 1)
                                if (a[i][j] < 'a')
                    if (b[i][j] != ' ' && r.nextInt(100) > unit[cnt].w1ac)
                {
                    switch (b[i][j])
                    {
                        case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage1; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; } break;
                        case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage1; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; } break;
                        case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage1; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; } break;
                        case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage1; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; } break;
                        case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage1; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; } break;
                        case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage1; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; } break;
                        case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage1; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; } break;
                        case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage1; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; } break;
                        case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage1; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; } break;
                        case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage1; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; } break;

                    }
                    Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage");
                }
                                            else
                Controller.TextBox1.setText( "Miss");
                break;
                case 0:
                    switch (unit[cnt].I - coordinate_i)
                    {
                        case 5:
                            for (int i = 0; i < 11; i++)
                                for (int j = 0; j < 19; j++)
                                    if ((i - coordinate_i) <= 3 && (i - coordinate_i) >= 1 && Math.abs(coordinate_j - j) <= 1)
                                        if (a[i][j] < 'a')
                            if (b[i][j] != ' ' && r.nextInt(100) > unit[cnt].w1ac)
                        {
                            switch (b[i][j])
                            {
                                case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage1; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; } break;
                                case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage1; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; } break;
                                case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage1; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; } break;
                                case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage1; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; } break;
                                case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage1; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; } break;
                                case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage1; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; } break;
                                case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage1; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; } break;
                                case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage1; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; } break;
                                case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage1; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; } break;
                                case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage1; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; } break;

                            }
                            Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage");
                        }
                                                    else
                        Controller.TextBox1.setText( "Miss");
                        break;


                        case -5:
                            for (int i = 0; i < 11; i++)
                                for (int j = 0; j < 19; j++)
                                    if ((i - coordinate_i) <= -1 && (i - coordinate_i) >= -3 && Math.abs(coordinate_j - j) <= 1)
                                        if (a[i][j] < 'a')
                            if (b[i][j] != ' ' && r.nextInt(100) > unit[cnt].w1ac)
                        {
                            switch (b[i][j])
                            {
                                case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage1; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; } break;
                                case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage1; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; } break;
                                case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage1; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; } break;
                                case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage1; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; } break;
                                case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage1; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; } break;
                                case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage1; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; } break;
                                case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage1; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; } break;
                                case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage1; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; } break;
                                case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage1; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; } break;
                                case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage1; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; } break;

                            }
                            Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage");
                        }
                                                    else
                        Controller.TextBox1.setText( "Miss");
                        break;
                    }
                    break;
            }


                ; unit[cnt].AP = 0; b[coordinate_i][coordinate_i] = unit[cnt].id;
                b[unit[cnt].I][unit[cnt].J] = ' ';
                unit[cnt].I = coordinate_i;
                unit[cnt].J = coordinate_j; break;

            case "Tank":
                if (a[coordinate_i][coordinate_j] < 'a')
                if (b[coordinate_i][coordinate_j] == ' ')
            { b[coordinate_i][coordinate_j] = '#'; rendering1(); }
                        else
            Controller.TextBox1.setText( "Can't make order");
            unit[cnt].AP--;
            break;
            case "Reaper":switch (unit[cnt].J - coordinate_j)
            {
                case 1:
                    for (int i = 0; i < 11; i++)
                        for (int j = 0; j < 19; j++)
                            if ((j - coordinate_j) == -1 && Math.abs(coordinate_i - i) <= 0 || (j - coordinate_j) == -2 && Math.abs(coordinate_i - i) <= 1|| (j - coordinate_j) == -3 && Math.abs(coordinate_i - i) <= 2  )
                                if (a[i][j] < 'a')
                    if (b[i][j] != ' ' && r.nextInt(100) > unit[cnt].w1ac)
                {
                    for (int n=0;n<2;n++)
                        switch (b[i][j])
                    {
                        case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage1; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; } break;
                        case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage1; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; } break;
                        case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage1; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; } break;
                        case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage1; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; } break;
                        case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage1; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; } break;
                        case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage1; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; } break;
                        case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage1; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; } break;
                        case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage1; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; } break;
                        case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage1; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; } break;
                        case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage1; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; } break;

                    }
                    Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage");
                }
                                                else
                Controller.TextBox1.setText( "Miss");
                break;


                case -1:
                    for (int i = 0; i < 11; i++)
                        for (int j = 0; j < 19; j++)
                            if ((j - coordinate_j) == 1 && Math.abs(coordinate_i - i) <= 0 || (j - coordinate_j) == 2 && Math.abs(coordinate_i - i) <= 1 || (j - coordinate_j) == 3 && Math.abs(coordinate_i - i) <= 2)
                                if (a[i][j] < 'a')
                    if (b[i][j] != ' ' && r.nextInt(100) > unit[cnt].w1ac)
                {
                    for (int n = 0; n < 2; n++)
                        switch (b[i][ j])
                    {
                        case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage1; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; } break;
                        case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage1; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; } break;
                        case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage1; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; } break;
                        case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage1; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; } break;
                        case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage1; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; } break;
                        case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage1; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; } break;
                        case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage1; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; } break;
                        case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage1; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; } break;
                        case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage1; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; } break;
                        case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage1; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; } break;

                    }
                    Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage");
                }
                                            else
                Controller.TextBox1.setText( "Miss");
                break;
                case 0:
                    switch (unit[cnt].I - coordinate_i)
                    {
                        case 1:
                            for (int i = 0; i < 11; i++)
                                for (int j = 0; j < 19; j++)
                                    if ((i - coordinate_i) == -1 && Math.abs(coordinate_j - j) <= 0 || (i - coordinate_i) == -2 && Math.abs(coordinate_j - j) <= 1 || (i - coordinate_i) == -3 && Math.abs(coordinate_j - j) <= 2)
                                        if (a[i][ j] < 'a')
                            if (b[i][ j] != ' ' && r.nextInt(100) > unit[cnt].w1ac)
                        {
                            for (int n = 0; n < 2; n++)
                                switch (b[i][ j])
                            {
                                case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage1; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; } break;
                                case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage1; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; } break;
                                case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage1; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; } break;
                                case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage1; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; } break;
                                case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage1; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; } break;
                                case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage1; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; } break;
                                case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage1; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; } break;
                                case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage1; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; } break;
                                case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage1; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; } break;
                                case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage1; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; } break;

                            }
                            Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage");
                        }
                                                    else
                        Controller.TextBox1.setText( "Miss");
                        break;


                        case -1:
                            for (int i = 0; i < 11; i++)
                                for (int j = 0; j < 19; j++)
                                    if ((i - coordinate_i) == 1 && Math.abs(coordinate_j - j) <= 0 || (i - coordinate_i) == 2 && Math.abs(coordinate_j - j) <= 1 || (i - coordinate_i) == 3 && Math.abs(coordinate_j - j) <= 2)
                                        if (a[i][ j] < 'a')
                            if (b[i][ j] != ' ' && r.nextInt(100) > unit[cnt].w1ac)
                        {
                            for (int n = 0; n < 2; n++)
                                switch (b[i][ j])
                            {
                                case 'A': switch (unit[10].Type) { case "Tank": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[10].HP = unit[10].HP - unit[cnt].damage1; ; break; case "Reaper": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Art": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Ghoust": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; case "Laser": unit[10].HP = unit[10].HP - unit[cnt].damage1; break; } break;
                                case 'B': switch (unit[11].Type) { case "Tank": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[11].HP = unit[11].HP - unit[cnt].damage1; ; break; case "Reaper": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Art": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Ghoust": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; case "Laser": unit[11].HP = unit[11].HP - unit[cnt].damage1; break; } break;
                                case 'C': switch (unit[12].Type) { case "Tank": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[12].HP = unit[12].HP - unit[cnt].damage1; ; break; case "Reaper": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Art": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Ghoust": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; case "Laser": unit[12].HP = unit[12].HP - unit[cnt].damage1; break; } break;
                                case 'D': switch (unit[13].Type) { case "Tank": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[13].HP = unit[13].HP - unit[cnt].damage1; ; break; case "Reaper": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Art": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Ghoust": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; case "Laser": unit[13].HP = unit[13].HP - unit[cnt].damage1; break; } break;
                                case 'E': switch (unit[14].Type) { case "Tank": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[14].HP = unit[14].HP - unit[cnt].damage1; ; break; case "Reaper": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Art": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Ghoust": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; case "Laser": unit[14].HP = unit[14].HP - unit[cnt].damage1; break; } break;
                                case 'F': switch (unit[15].Type) { case "Tank": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[15].HP = unit[15].HP - unit[cnt].damage1; ; break; case "Reaper": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Art": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Ghoust": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; case "Laser": unit[15].HP = unit[15].HP - unit[cnt].damage1; break; } break;
                                case 'G': switch (unit[16].Type) { case "Tank": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[16].HP = unit[16].HP - unit[cnt].damage1; ; break; case "Reaper": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Art": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Ghoust": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; case "Laser": unit[16].HP = unit[16].HP - unit[cnt].damage1; break; } break;
                                case 'H': switch (unit[17].Type) { case "Tank": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[17].HP = unit[17].HP - unit[cnt].damage1; ; break; case "Reaper": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Art": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Ghoust": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; case "Laser": unit[17].HP = unit[17].HP - unit[cnt].damage1; break; } break;
                                case 'I': switch (unit[18].Type) { case "Tank": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[18].HP = unit[18].HP - unit[cnt].damage1; ; break; case "Reaper": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Art": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Ghoust": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; case "Laser": unit[18].HP = unit[18].HP - unit[cnt].damage1; break; } break;
                                case 'J': switch (unit[19].Type) { case "Tank": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Jet": if (unit[cnt].Type == "Reaper" || unit[cnt].Type == "Jet" || unit[cnt].Type == "Laser") unit[19].HP = unit[19].HP - unit[cnt].damage1; ; break; case "Reaper": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Art": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Ghoust": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; case "Laser": unit[19].HP = unit[19].HP - unit[cnt].damage1; break; } break;

                            }
                            Controller.TextBox1.setText( "Enemy unit take " + unit[cnt].damage1 + " damage");
                        }
                                                    else
                        Controller.TextBox1.setText( "Miss");
                        break;
                    }
                    break;
            }


                unit[cnt].AP = 0; break;
        }

        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 19; j++)
                switch (a[i][ j])
        {
            case 'R': a[i][ j] = 'r'; break;
            case 'W': a[i][ j] = 'w'; break;
            case 'T': a[i][ j] = 't'; break;
            case 'G': a[i][ j] = 'g'; break;
            case 'P': a[i][ j] = 'p'; break;
        }

        unit[cnt].AP = unit[cnt].AP;

        rendering1();
        context = "select"; coordinate_i = unit[cnt].I; coordinate_j = unit[cnt].J; selecting();

    }
    //}

}
