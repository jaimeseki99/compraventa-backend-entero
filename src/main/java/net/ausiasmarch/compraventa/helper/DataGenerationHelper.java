package net.ausiasmarch.compraventa.helper;

import java.util.Random;

public class DataGenerationHelper {

    private static final String[] nombres = { "Mónica", "Jose Antonio", "Laura", "Lucas", "Eva", "Eloy", "Jesús", "Alan", "Pablo", "Paula", "Raquel", "Nieves", "Elena", "Sergio", "Fernando", "Jaime", "Vicente", "Ana", "Andrea", "Raúl", "Carlos", "Manuel", "Alejandro", "Sandra", "Lorena", "Antonio", "David", "Victor", "Jorge", "Alberto", "Óscar", "Carmen", "Miguel", "Alba", "Laia", "Juan", "Santiago", "Ramón", "Ainara", "Claudia", "Unai", "Josep", "Oriol", "María", "Teresa", "Ibai", "Iñaki", "Isabel", "Rosa", "Rocío", "Lionel", "Anna", "Rafael", "Ona", "Samuel", "Iñigo", "Carles", "Guillermo", "Guillem", "Julia", "Benito", "Marta", "Sofía", "David", "Julián"};

    private static final String[] apellidos = { "Alcañiz", "Puig", "Ayala", "Farell", "Ferrer", "Esteve", "González", "Rozalén", "Lara", "Velarte", "Latorre", "Briones", "Maldonado", "Suárez", "McLure", "Alarcón", "Molero", "Marín", "Muñoz", "García", "Navarro", "López", "Navas", "Aguilar", "Ortega", "Fabra", "Romero", "Díaz", "Roselló", "Gómez", "Serrano", "Quílez", "Martínez", "Sánchez", "Álvarez", "Fernández", "Roldán", "Izquierdo", "Marqueño", "Mozo", "Baeza", "Sierra", "Pascual", "Marqueño", "Soriano", "Pérez", "Hervás", "Gutiérrez", "Manrique", "Pastor", "Núñez", "Benítez", "Ordoñez", "Peris", "Romeu", "Sanchis", "Calatayud", "Aznar", "Aparaci", "Messi", "Castillo", "Melano", "Llanos", "Vargas"};

    private static final String[] ciudades = {"A Coruña", "Santiago de Compostela", "Vigo", "Ourense", "Pontevedra", "Lugo", "Gijón", "Oviedo", "Santander", "Logroño", "Bilbao", "San Sebastián", "Vitoria", "Pamplona", "Teruel", "Huesca", "Zaragoza", "Barcelona", "Tarragona", "Girona", "Lleida", "Valencia", "Alicante", "Castellón", "Palma de Mallorca", "Menorca", "Ibiza", "Murcia", "Albacete", "Ciudad Real", "Toledo", "Guadalajara", "Cuenca", "Madrid", "León", "Valladolid", "Segovia", "Ávila", "Salamanca", "Soria", "Burgos", "Zamora", "Palencia", "Cádiz", "Huelva", "Sevilla", "Granada", "Almería", "Jaén", "Córdoba", "Málaga", "Cáceres", "Badajoz", "Tenerife", "Las Palmas de Gran Canaria"};

    private static final String[] tipoCalle = {"Calle", "Plaza", "Avenida", "Paseo", "Vía"};

    private static final String[] nombreCalle = {"Colón", "Puerto", "La Plata", "Real", "Reino", "República", "9 de octubre", "2 de mayo", "Gran Vía", "Alcalá", "Prado", "Serrano", "Mayor", "Fuencarral", "Preciados", "Castellana", "Gracia", "Moncada", "Diagonal", "Ferran", "Provencial", "Ruzafa", "Castro", "San Vicente", "Las Barcas", "Los Peregrinos", "Feria", "Pedro Sánchez", "Mariano Rajoy", "Falsa", "Príncipe Felipe", "Evergreen Terrace", "Constitución", "La Virgen", "Asunción", "Santo Tomás", "San Juan", "Independencia", "Viñeda", "Cervantes", "Mayor", "Charizard", "España", "Aragón", "Baleares", "Cataluña", "Canarias", "Galicia", "Andalucía", "País Vasco", "Valencia", "Castilla", "Felipe VI", "Asturias"};

    private static final String[] nombresProducto = {"Chaqueta", "Patatas", "Bombones", "Surtido de snacks", "Sudadera", "Smartphone", "Portátil", "Pelota de fútbol", "Pelota de tenis", "Pelota de baloncesto", "Helado de chocolate", "Helado de fresa", "Helado de vainilla", "Caramelos", "Turrones", "PC Gamer", "Calcetines", "Pijama", "Refrescos", "Agua mineral", "Zapatillas", "Ordenador de sobremesa", "Fajitas", "Hamburguesas", "Mayonesa", "Ketchup", "Mostaza", "Lejía", "Detergente", "Lavadora", "Consola", "Arroz", "Tallarines", "Pimientos", "Linterna", "Pilas", "Camiseta", "Videojuego", "Cerveza", "Vino", "Jamón", "Calamares a la Romana"};

    private static final String[] marcas = {"Samsung", "LG", "Adidas", "Nike", "Puma", "Joma", "Castore", "El Abuelo", "FIFA", "Suchard", "Lindt", "Old El Paso", "CocaCola", "Fanta", "Doritos", "Cheetos", "Artesanales", "Jijonenca", "Apple", "Lanjarón", "Bezoya", "Asus", "Astro", "Acer", "Tesla", "Chicken Garden", "Hacendado", "Air Jordan", "Zara", "Pull&Bear", "Springfield", "H&M", "Pokémon", "Planes", "Pollos Hermanos", "LimpiezaAbsoluta", "Sanitoleo", "Las 3 brujas", "Vitroclen", "LaLiga", "EA", "NBA"};

    private static final String[] categorias = {"Electrónica", "Bebidas", "Snacks", "Comida", "Carne", "Pescado", "Ropa", "Limpieza", "Postres", "Deportes", "Smartphones"};

    private static final String[] palabrasDescripcion = {
        "innovador",
        "elegante",
        "cómodo",
        "moderno",
        "eficiente",
        "exclusivo",
        "delicioso",
        "nutritivo",
        "relajante",
        "divertido"
    };

    public static String getNombreRandom() {
        return nombres[(int) (Math.random() * nombres.length)];
    }

    public static String getApellidoRandom() {
        return apellidos[(int) (Math.random() * nombres.length)];
    }

    public static String doNormalizeString(String cadena) {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String cadenaSinAcentos = cadena;
        for (int i = 0; i < original.length(); i++) {
            cadenaSinAcentos = cadenaSinAcentos.replace(original.charAt(i), ascii.charAt(i));
        }
        return cadenaSinAcentos;
    }

    public static String generarNumeroTelefono() {
        String primerDigito = String.valueOf(6 + new Random().nextInt(2));
        String restoNumero = generarNumeroRestante();
        return primerDigito + restoNumero;
    }

    public static String generarNumeroRestante() {
        Random random = new Random();
        StringBuilder numeroRestante = new StringBuilder();
        for (int i = 0; i<8; i++) {
            numeroRestante.append(random.nextInt(10));
        }
        return numeroRestante.toString();
    }

    public static String getCiudadRandom() {
        return ciudades[(int) (Math.random() * ciudades.length)];
    }

    public static String getTipoCalleRandom() {
        return tipoCalle[(int) (Math.random() * tipoCalle.length)];
    }

    public static String getNombreCalleRandom() {
        return nombreCalle[(int) (Math.random() * nombreCalle.length)];
    }

    public static String generarDireccionRandom() {
        Random random = new Random();
        StringBuilder nuevaCalle = new StringBuilder();

        nuevaCalle.append(getTipoCalleRandom());
        nuevaCalle.append("");
        nuevaCalle.append(getNombreCalleRandom());
        nuevaCalle.append(random.nextInt(150));
        nuevaCalle.append(",");
        nuevaCalle.append(getCiudadRandom());
        
        return nuevaCalle.toString();
    }

    public static double generarDobleRandom() {
        Random random = new Random();
        return random.nextDouble(100000);
    }

    public static int generarIntRandom() {
        Random random = new Random();
        return random.nextInt(10000);
    }

   
    public static String getNombreProductoRandom() {
        return nombresProducto[(int) (Math.random() * nombresProducto.length)];
    }

    public static String getMarcaRandom() {
        return marcas[(int) (Math.random() * marcas.length)];
    }

    public static String generarProductoRandom() {
        StringBuilder nuevoProducto = new StringBuilder();
        nuevoProducto.append(getNombreProductoRandom());
        nuevoProducto.append("");
        nuevoProducto.append(getMarcaRandom());

        return nuevoProducto.toString();
    }

    public static String getPalabraDescripcion() {
        return palabrasDescripcion[(int) (Math.random() * palabrasDescripcion.length)];
    }

    public static String generarDescripcion() {
        StringBuilder descripcion = new StringBuilder();
        descripcion.append("Producto :");
        for (int i=0; i<3; i++) {
            descripcion.append(getPalabraDescripcion()).append(",");
        }
        descripcion.append(" y ").append(getPalabraDescripcion()).append(".");
        return descripcion.toString();
    }

    public static String getCategoriaRandom() {
        return categorias[(int) (Math.random() * categorias.length)];
    }

}
