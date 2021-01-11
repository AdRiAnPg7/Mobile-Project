package edu.bo.framework.localData

import com.e.data.IRemoteDataSource
import com.e.domain.Post

class PostLocalData():IRemoteDataSource {
    val list: MutableList<Post> = mutableListOf(
    Post("Busco a mi mascotita plox","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","05/05/2020","Chocolate","Gato", "Desconocida", "Cafe","76452269","Al contrario del pensamiento popular, el texto de Lorem Ipsum no es simplemente texto aleatorio. Tiene sus raices en una pieza cl´sica de la literatura del Latin, que data del año 45 antes de Cristo, haciendo que este adquiera mas de 2000 años de antiguedad.","1"),
    Post("Perrita dalmata encontrada","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvHqDrP5JQtlTi6bbuhtkWnfjPSCyNg3Hh8g&usqp=CAU.jpg","15/06/2020","Desconocido","Perro","Dalmata", "Blanco con manchas negras","68745221","Hay muchas variaciones de los pasajes de Lorem Ipsum disponibles, pero la mayoría sufrió alteraciones en alguna manera, ya sea porque se le agregó humor, o palabras aleatorias que no parecen ni un poco creíbles. Si vas a utilizar un pasaje de Lorem Ipsum.","2"),
    Post("Mi pequeño caniche se perdio","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQosulmGCnFnvQ8mB-N-hKzx7h-q5dZTDSHiA&usqp=CAU.jpg","29/06/2020","Coco","Perro","Caniche","Negro","78552233","Es un hecho establecido hace demasiado tiempo que un lector se distraerá con el contenido del texto de un sitio mientras que mira su diseño.","3"),
    Post("Busco a mi perrito esta en tratamiento","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","05/08/2020","Thor","Perro", "Braco Frances","Blanco con manchas cafes","71235542","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nec faucibus turpis, nec interdum nibh. Maecenas ullamcorper nec turpis et sollicitudin.","4"),
    Post("Se perdio mi gatita","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","08/08/2020","Amaya","Gato", "Abisinio","Pardo","68952231","Nullam elementum porttitor purus egestas aliquam. In id aliquet odio. In pretium, ex non vehicula sollicitudin, nunc dui gravida neque, molestie pretium odio odio eu nibh.","5"),
    Post("Encontre este cachorro","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","01/10/2020","Desconocido","Perro","Chow Chow","Pardo","79845211","Vestibulum vel accumsan diam. Nunc sed efficitur quam, non condimentum lacus. Suspendisse euismod varius mauris nec tristique.","6"),
    Post("Se busca a mi perrito","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","02/10/2020","Lucas","Perro", "Pomeriana","Blanco","69321104","Ut vitae accumsan nunc. Nullam vulputate ligula in augue placerat fringilla. Integer auctor elit turpis, in placerat odio egestas in.","7"),
    Post("Se perdio por la ciudad","https://image.freepik.com/foto-gratis/perro-perdido_72464-1282.jpg","15/12/2020","Max","Gato","Desconocida","Negro","70412237","Etiam mollis placerat odio, sed tristique diam condimentum ut. Mauris sollicitudin, arcu vitae eleifend placerat, nulla est semper ligula.","8")
    )
    override fun getAllPosts(): List<Post> {
        return list
    }

    override fun addPost(post: Post) {
        list.add(post)
    }
}