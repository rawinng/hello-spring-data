package rawinng.hellospringdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@SpringBootApplication
class HelloSpringDataRestApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringDataRestApplication>(*args)
}

@Entity
data class Client(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
                  val firstName: String,
                  val lastName: String,
                  val email: String,
                  val phoneNumber: Int,
                  @OneToOne(cascade = [CascadeType.ALL])
                  @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
                  val address: Address? = null)

@Entity
data class Address(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                   val id: Long,
                   val addressLine1: String,
                   val addressLine2: String,
                   val city: String,
                   val state: String,
                   val zipCode: Int,
                   @OneToOne(mappedBy = "address")
                   val client: Client)

interface AddressRepository : JpaRepository<Address, Long> {}

interface ClientRepository : JpaRepository<Client, Long> {}