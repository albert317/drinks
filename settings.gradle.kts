pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "InfiniteSpirit"
include(":app")
include(":common")
include(":common:network")
include(":features")
include(":features:onboarding")

include(":features:onboarding:presentation")
include(":features:onboarding:domain")
include(":features:onboarding:usecase")
include(":features:onboarding:data")
include(":common:firebase")
include(":common:designsystem")
include(":common:presentation")

include(":features:drink")

include(":features:drink:presentation")
include(":features:drink:data")
include(":features:drink:domain")
include(":features:drink:usecase")
include(":common:domain")
include(":common:navigation")
