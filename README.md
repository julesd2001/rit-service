# rit-service
Dit is de rit-service back-end microservice voor het project Advanced Programming Topics van Jules Debbaut en Walid Amhaini. Deze service houdt zich voornamelijk bezig met de verschillende ritten die door vrachtwagens gedaan worden, en bovendien ook wat elke vrachtwagen kan meenemen qua cargo.
De service heeft in totaal 11 GET-endpoints, en 1 POST, PUT en DELETE endpoint voor ritten en cargo.

# GET /ritten
Met deze request halen we alle ritten op die in de database zitten.
![image](https://user-images.githubusercontent.com/57659236/148534736-c4a14fd6-f17f-4877-94f6-2152c59a157b.png)

# GET /ritten/ritlengte/{ritlengte}
Met deze request kunnen we alle ritten ophalen in de database die de gegeven ritlengte hebben.
![image](https://user-images.githubusercontent.com/57659236/148534842-7409eb9f-d530-4d31-9fed-8416ab53d4cb.png)

# GET /ritten/vertrekpunt/{vertrekpunt}
Met deze request kunnen we alle ritten ophalen waarbij het vertrekpunt overeenkomt met de variabele dat we in de URL meegeven.
![image](https://user-images.githubusercontent.com/57659236/148534959-9a9a28eb-5d62-4899-a0c3-61f903bc7b63.png)

# GET /ritten/bestemming/{bestemming}
Met deze request kunnen we alle ritten ophalen waarbij de bestemming overeenkomt met de variabele die in de URL meegegeven wordt.
![image](https://user-images.githubusercontent.com/57659236/148535060-2004887d-ea64-4289-8660-be8a02c494b9.png)

# GET /ritten/{ritId}
Met deze request halen we de rit op in de database waarmee de ritId overeenkomt met de ritId in de database.
![image](https://user-images.githubusercontent.com/57659236/148535161-82940e71-78b2-4c14-b74f-ed63cd392999.png)

# GET /ritten/cargo/cargoId
Hiermee halen we alle ritten op waarmee het cargoId gelijk is aan de cargoId van een rit in de database.
![image](https://user-images.githubusercontent.com/57659236/148535300-6bd8d7cf-c4cf-46c1-9c2f-5694d3b6c547.png)

# GET /ritten/nummerplaat/{nummerplaat}
Met deze request halen we alle ritten op die een vrachtwagen met het gegeven nummerplaat gedaan heeft. 
![image](https://user-images.githubusercontent.com/57659236/148535430-e7a70087-5f51-4fa5-902c-355c1c266a05.png)

# GET /ritten/nummerplaat/{nummerplaat}/vertrekpunt/{vertrekpunt}
Hiermee halen we alle ritten op voor een vrachtwagen met een bepaalde nummerplaat, en bovendien ook een bepaald vertrekpunt.
![image](https://user-images.githubusercontent.com/57659236/148535581-7a770575-fb88-49ee-ab00-9784f65d1cec.png)

# GET /ritten/nummerplaat/{nummerplaat}/bestemming/{bestemming}
Hiermee halen we alle ritten op voor een vrachtwagen met een bepaalde nummerplaat, en bovendien ook een bepaalde bestemming.
![image](https://user-images.githubusercontent.com/57659236/148535674-4bbdc88f-28d4-4318-9bc5-b16bdc258291.png)

# POST /ritten
Met deze request kunnen we nieuwe ritten in onze database aanmaken.
![image](https://user-images.githubusercontent.com/57659236/148535902-7945f6d7-6637-46ef-b76f-1144c844c31d.png)
![image](https://user-images.githubusercontent.com/57659236/148535925-85140d91-c5a1-4e50-a593-4cc08e25e1f8.png)

# PUT /ritten
Met deze request kunnen we onze ritten aanpassen.

# DELETE /ritten/{ritId}
Deze request zorgt er dan voor dat we een bepaalde rit uit de database kunnen verwijderen.
![image](https://user-images.githubusercontent.com/57659236/148536088-f9fcc993-5da2-4820-93e7-a031776bf329.png)

