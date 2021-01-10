package edu.bo.framework.localData

import com.e.data.IRemoteDataSource
import com.e.domain.Post

class PostLocalData():IRemoteDataSource {
    val list: MutableList<Post> = mutableListOf(
    Post("Busco a mi mascotita plox","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","https://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/07/25/Recortada/img_econcejo_20180730-165058_imagenes_lv_terceros_istock-894361286-k0hH--656x438@LaVanguardia-Web.jpg","Alejandro Campero","05/05/2020","Chocolate","Gato", "Desconocida", "Cafe","76452269","Al contrario del pensamiento popular, el texto de Lorem Ipsum no es simplemente texto aleatorio. Tiene sus raices en una pieza cl´sica de la literatura del Latin, que data del año 45 antes de Cristo, haciendo que este adquiera mas de 2000 años de antiguedad."),
    Post("Perrita dalmata encontrada","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvHqDrP5JQtlTi6bbuhtkWnfjPSCyNg3Hh8g&usqp=CAU.jpg","https://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/07/25/Recortada/img_econcejo_20180730-165058_imagenes_lv_terceros_istock-894361286-k0hH--656x438@LaVanguardia-Web.jpg","Willson Vargas Canelas","15/06/2020","Desconocido","Perro","Dalmata", "Blanco con manchas negras","68745221","Hay muchas variaciones de los pasajes de Lorem Ipsum disponibles, pero la mayoría sufrió alteraciones en alguna manera, ya sea porque se le agregó humor, o palabras aleatorias que no parecen ni un poco creíbles. Si vas a utilizar un pasaje de Lorem Ipsum."),
    Post("Mi pequeño caniche se perdio","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQosulmGCnFnvQ8mB-N-hKzx7h-q5dZTDSHiA&usqp=CAU.jpg","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Ariel Padilla Vega","29/06/2020","Coco","Perro","Caniche","Negro","78552233","Es un hecho establecido hace demasiado tiempo que un lector se distraerá con el contenido del texto de un sitio mientras que mira su diseño."),
    Post("Busco a mi perrito esta en tratamiento","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Tania Revilla Conde","05/08/2020","Thor","Perro", "Braco Frances","Blanco con manchas cafes","71235542","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nec faucibus turpis, nec interdum nibh. Maecenas ullamcorper nec turpis et sollicitudin."),
    Post("Se perdio mi gatita","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Jorge Bernabe","08/08/2020","Amaya","Gato", "Abisinio","Pardo","68952231","Nullam elementum porttitor purus egestas aliquam. In id aliquet odio. In pretium, ex non vehicula sollicitudin, nunc dui gravida neque, molestie pretium odio odio eu nibh."),
    Post("Encontre este cachorro","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","https://i.pinimg.com/736x/5f/49/e1/5f49e11efad480bf5be95e2cd30332f4.jpg","Ximena Arancibia","01/10/2020","Desconocido","Perro","Chow Chow","Pardo","79845211","Vestibulum vel accumsan diam. Nunc sed efficitur quam, non condimentum lacus. Suspendisse euismod varius mauris nec tristique."),
    Post("Se busca a mi perrito","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Marco Soliz Pardo","02/10/2020","Lucas","Perro", "Pomeriana","Blanco","69321104","Ut vitae accumsan nunc. Nullam vulputate ligula in augue placerat fringilla. Integer auctor elit turpis, in placerat odio egestas in."),
    Post("Se perdio por la ciudad","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","https://www.seoptimer.com/es/blog/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg","Juan Jose Gonzales ","15/12/2020","Max","Gato","Desconocida","Negro","70412237","Etiam mollis placerat odio, sed tristique diam condimentum ut. Mauris sollicitudin, arcu vitae eleifend placerat, nulla est semper ligula.")
    )
    override fun getAllPosts(): List<Post> {
        return list
    }

    override fun addPost(post: Post) {
        list.add(post)
    }
}