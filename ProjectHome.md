# Introduction #

The **Climate Smart Seaports** Tool enables interested users to begin the process of a climate risk assessment.

It is designed primarily for port personnel who make (or influence) decisions around long-term port planning for infrastructure, assets and management systems. However, it will also be of value to port owners and related businesses, government departments and local authorities concerned with ports and infrastructure; and for application by academic researchers.

It assists them to identify current and historical climate trends and variability, as well as future climate projections under a variety of scenarios. Population and trade data is included, and users can add port-specific information to round out their analysis.


The application can be used to create a "_workboard_" and add data originating from
various sources data sets from [BoM](http://www.bom.gov.au/), [CSIRO](http://www.csiro.au/), [ABS](http://www.abs.gov.au/), [Ports Australia](http://www.portsaustralia.com.au/), etc. They can also work with an external engineering tool which generates more customized data and import this data to their "_workboard_".

After gathering the piece of data they need, users can then create reports and publish them, both on the Climate Smart Seaports portal to make them accessible and searchable by all and to [Research Data Australia](http://researchdata.ands.org.au/), a service from [ANDS](http://ands.org.au/) which facilitates search and reuse of research data.

## Context ##

Adapting to climate change is still in its infancy and all organisations are learning how to manage the risks posed by the changing climate. One way to learn is by communication. One of the aims of this tool is that users will publish their generated reports to the Climate Smart Seaports site, promoting peer-to-peer learning and the spread of knowledge.

Often, risk management is confined to specific areas of a business operation, such as risks related to trade, to supply chains to the workplace functions or infrastructure. However, climate change poses a risk to all areas of a business. This tool attempts to bring the thinking about climate risks in different areas of the business into one report, beginning the creation of an integrated climate risk assessment.

The main purpose of the tool is to facilitate access to multiple sources of information so that ports are able to identify future risks and adaptation options.

## Documentation ##

[User Documentation and Guidelines](https://docs.google.com/a/rmit.edu.au/file/d/0B5wr2axc4lYHMHRSbG9JRnFkX00/edit) (1.78MB pdf file)

[Developer Installation and Deployment guide](https://docs.google.com/a/rmit.edu.au/file/d/0B5wr2axc4lYHMndlSmVYY0ZaYTg/edit) (1.37MB pdf file)

[Developer Documentation](https://docs.google.com/a/rmit.edu.au/file/d/0B5wr2axc4lYHVW5keDVNZERLeVU/edit) (3.38MB pdf file)

All documents downloads are available on [Google Drive](https://drive.google.com/a/rmit.edu.au/#folders/0B5wr2axc4lYHUUgyVDRnc0dDRmM)

## Technical Overview ##

The project has been developed in Java, using the Spring MVC framework, Spring security for authentication, Hibernate as ORM layer to persist the data in a MySQL database, and a Java library to generate ANDS's RIF-CS format. The web application is built and deployed using Maven and Jetty.

The main technical challenge of this project consisted in the aggregation of heterogeneous data sources into one single platform, and allow the user interacting seamlessly with any of them. Several mechanisms have been implemented to face that issue. The most important one resides in the modelling of the data sets: it is giving a certain level of abstraction which considers any piece of data from the varied data sets as a generic "data element", so that adding a new data source doesn't affect how the rest of the application works.


---


## Acknowledgments ##

The Climate Smart Seaports project includes development funded by the _Australian National Data Service_ (ANDS, http://ands.org.au/ ). ANDS is supported by the _Australian Government_ through the _National Collaborative Research Infrastructure Strategy Program_ and the _Education Investment Fund (EIF) Super Science Initiative_.